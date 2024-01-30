package org.fan.cloud.auth.service.impl;

import org.fan.cloud.auth.dao.FanSystemAuthDao;
import org.fan.cloud.auth.entity.Permission;
import org.fan.cloud.auth.entity.QueryAuth;
import org.fan.cloud.auth.service.MenusMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fanfanlordship
 * @Description 移动菜单顺序
 */
@Service
public class MenusMoveServiceImpl implements MenusMoveService {

    @Autowired
    private FanSystemAuthDao fanSystemAuthDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveUp(String id) {
        this.move(id, "up");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveDown(String id) {
        this.move(id, "down");
    }

    private void move(String id, String moveTo) {
        // 查询菜单所在层级
        QueryAuth queryAuth = new QueryAuth();
        queryAuth.setAuthId(id);
        List<Permission> auths = fanSystemAuthDao.queryBrother(queryAuth);
        LinkedList<Permission> collect = auths.stream().sorted(Comparator.comparing(Permission::getSort)).collect(Collectors.toCollection(LinkedList::new));
        ListIterator<Permission> iterator = collect.listIterator();
        while (iterator.hasNext()) {
            Permission moveAuth = iterator.next();
            if (!Objects.equals(id, moveAuth.getId())) {
                continue;
            }
            // 上移的话和前一个交换
            Permission moveBy;
            if (Objects.equals("up", moveTo)) {
                // 如果没有前一个，则无法移动
                if (!iterator.hasPrevious()) {
                    break;
                }
                moveBy = iterator.previous();
            } else {
                if (!iterator.hasNext()) {
                    break;
                }
                moveBy = iterator.next();
            }

            Integer temp = moveBy.getSort();
            moveBy.setSort(moveAuth.getSort());
            moveAuth.setSort(temp);
            break;
        }
    }
}
