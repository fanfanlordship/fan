package org.fan.cloud.common.util;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.fan.cloud.common.entity.tree.TreeNode;
import org.fan.cloud.common.entity.tree.TreeNodeSort;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author fanfanlordship
 * @Date: 2023/10/11 16:59
 */
public class TreeUtils {

    private TreeUtils() {
    }

    /**
     * 将列表生成树
     *
     * @param list
     * @param <T>  必须是继承了TreeNode的对象集合
     * @return
     */
    public static <T extends TreeNode> List<T> toTree(List<T> list) {
        return toTree(list, false);
    }

    /**
     * 将列表生成树，并且排序
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends TreeNodeSort> List<T> toSortTree(List<T> list) {
        return toTree(list, true);
    }

    private static <T extends TreeNode> List<T> toTree(List<T> list, boolean sort) {
        LinkedListMultimap<String, T> linkedListMultimap = LinkedListMultimap.create();
        Map<String, T> map = Maps.newHashMapWithExpectedSize(list.size());

        for (T t : list) {
            linkedListMultimap.put(t.getParentId(), t);
            map.put(t.getId(), t);
        }

        for (String parentId : linkedListMultimap.keySet()) {
            List<T> treeNodes = linkedListMultimap.get(parentId);
            if (CollectionUtils.isEmpty(treeNodes)) {
                continue;
            }

            if (sort) {
                Collections.sort((List<TreeNodeSort>) list);
            }

            TreeNode treeNode = map.get(parentId);
            if (treeNode != null) {
                treeNode.setChildren(treeNodes);
            }
        }

        // 移除掉没有上级ID或查询不到上级的数据
        Set<String> removeIds = Sets.newHashSet();
        for (Map.Entry<String, T> item : map.entrySet()) {
            String id = item.getKey();
            TreeNode value = item.getValue();
            if (value.getParentId() != null && map.get(value.getParentId()) != null) {
                removeIds.add(id);
            }
        }

        Set<String> rootKeys = map.keySet();
        if (!CollectionUtils.isEmpty(removeIds)) {
            rootKeys = rootKeys.stream().filter(o -> !removeIds.contains(o)).collect(Collectors.toSet());
        }

        List<T> nodes = Lists.newArrayListWithCapacity(rootKeys.size());
        for (String rootKey : rootKeys) {
            nodes.add(map.get(rootKey));
        }

        if (sort) {
            Collections.sort((List<TreeNodeSort>) nodes);
        }

        return nodes;
    }
}
