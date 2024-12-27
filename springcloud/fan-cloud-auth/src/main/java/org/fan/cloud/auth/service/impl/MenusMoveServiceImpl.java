package org.fan.cloud.auth.service.impl;

import org.fan.cloud.auth.dao.FanSystemAuthDao;
import org.fan.cloud.auth.entity.DatabaseArgs;
import org.fan.cloud.auth.entity.Menu;
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
        DatabaseArgs databaseArgs = new DatabaseArgs();
        databaseArgs.setIds(Collections.singletonList(id));
        List<Menu> auths = fanSystemAuthDao.queryBrother(databaseArgs);

        // 因为LinkList的previous返回依旧是本身，应该通过next取下一个
        Comparator<Menu> comparing = Comparator.comparing(Menu::getSort);
        if (Objects.equals("up", moveTo)) {
            comparing = comparing.reversed();
        }
        LinkedList<Menu> collect = auths.stream().sorted(comparing).collect(Collectors.toCollection(LinkedList::new));

        ListIterator<Menu> iterator = collect.listIterator();
        while (iterator.hasNext()) {
            Menu moveAuth = iterator.next();
            if (!Objects.equals(id, moveAuth.getId())) {
                continue;
            }
            if (!iterator.hasNext()) {
                break;
            }
            // 和下一个交换
            Menu moveBy = iterator.next();

            Integer temp = moveBy.getSort();
            moveBy.setSort(moveAuth.getSort());
            moveAuth.setSort(temp);

            fanSystemAuthDao.updateByPrimary(moveBy);
            fanSystemAuthDao.updateByPrimary(moveAuth);

            break;
        }
    }
}
