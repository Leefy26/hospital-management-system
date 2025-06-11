package com.hospital.system.service.impl;

import com.hospital.system.entity.LabItem;
import com.hospital.system.repository.LabItemRepository;
import com.hospital.system.service.LabItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 检验项目基础服务(LabItemService)的实现类
 * 负责处理检验项目相关的CRUD业务逻辑。
 */
@Service
public class LabItemServiceImpl implements LabItemService {

    // 自动注入 LabItemRepository，用于与数据库交互
    @Autowired
    private LabItemRepository labItemRepository;

    /**
     * 查询所有的检验项目
     * @return 检验项目列表
     */
    @Override
    public List<LabItem> findAll() {
        return labItemRepository.findAll();
    }

    /**
     * 根据ID查询单个检验项目
     * @param id 检验项目ID
     * @return 一个包含检验项目(如果找到)的Optional对象
     */
    @Override
    public Optional<LabItem> findById(Integer id) {
        return labItemRepository.findById(id);
    }

    /**
     * 保存或更新一个检验项目
     * @param labItem 需要保存的检验项目对象
     * @return 已保存的检验项目对象（包含数据库生成的ID）
     */
    @Override
    public LabItem save(LabItem labItem) {
        return labItemRepository.save(labItem);
    }

    /**
     * 根据ID删除一个检验项目
     * @param id 检验项目ID
     */
    @Override
    public void deleteById(Integer id) {
        labItemRepository.deleteById(id);
    }
}