package org.fan.cloud.common.entity.tree;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/**
 * @Author fanfanlordship
 * @Date: 2021/8/3 16:59
 * @Version 1.0
 */
public class TreeNodeSort<T extends TreeNode> extends TreeNode<T> implements Comparable<TreeNodeSort> {

    private Integer sort;

    public void sort() {
        List<TreeNodeSort<T>> children = (List<TreeNodeSort<T>>) getChildren();
        if (CollectionUtils.isEmpty(children)) {
            return;
        }
        Collections.sort(children);

        for (TreeNodeSort<T> child : children) {
            child.sort();
        }
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort == null ? -1 : sort;
    }

    @Override
    public int compareTo(TreeNodeSort o) {
        return this.sort - o.sort;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TreeNodeSort.class.getSimpleName() + "[", "]")
                .add("sort=" + sort)
                .add(super.toString())
                .toString();
    }
}
