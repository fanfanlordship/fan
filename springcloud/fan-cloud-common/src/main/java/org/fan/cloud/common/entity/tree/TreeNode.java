package org.fan.cloud.common.entity.tree;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.StringJoiner;

/**
 * @Author fanfanlordship
 * @Date: 2023/10/11 16:59
 */
public class TreeNode<T extends TreeNode> {
    /**
     * 主键
     */
    private String id;
    /**
     * 上级ID
     */
    private String parentId;
    /**
     * 子节点
     */
    private List<T> children;

    /**
     * 将所有子元素转成list（不包括本身）
     *
     * @return
     */
    public List<T> toList() {
        List<T> nodes = Lists.newArrayList();
        recursion(nodes, children);
        return nodes;
    }

    /**
     * 递归
     *
     * @param list
     * @param next
     */
    private void recursion(List<T> list, List<T> next) {
        if (CollectionUtils.isEmpty(next)) {
            return;
        }
        for (T t : next) {
            recursion(list, t.getChildren());
            list.add(t);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", children=" + children +
                '}';
    }
}
