package com.hospital.system.controller;

import com.hospital.system.entity.LabItem;
import com.hospital.system.service.LabItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/labitem")
public class LabItemController {

    @Autowired
    private LabItemService labItemService;

    /**
     * 显示所有检验项目列表
     */
    @GetMapping("/list")
    public String listLabItems(Model model) {
        model.addAttribute("labItemList", labItemService.findAll());
        return "lab_item_list";
    }

    /**
     * 显示新增检验项目的表单
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("labItem", new LabItem());
        model.addAttribute("action", "add");
        return "lab_item_form";
    }

    /**
     * 【修正关键点1】处理新增的表单提交，必须用 @PostMapping
     */
    @PostMapping("/add")
    public String addLabItem(@ModelAttribute LabItem labItem, RedirectAttributes redirectAttributes) {
        labItemService.save(labItem);
        redirectAttributes.addFlashAttribute("successMessage", "检验项目 '" + labItem.getName() + "' 新增成功！");
        return "redirect:/labitem/list";
    }

    /**
     * 显示修改检验项目的表单
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<LabItem> labItem = labItemService.findById(id);
        if (labItem.isPresent()) {
            model.addAttribute("labItem", labItem.get());
            model.addAttribute("action", "edit");
            return "lab_item_form";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "找不到ID为 " + id + " 的检验项目。");
        return "redirect:/labitem/list";
    }

    /**
     * 【修正关键点2】处理修改的表单提交，也必须用 @PostMapping
     */
    @PostMapping("/update")
    public String updateLabItem(@ModelAttribute LabItem labItem, RedirectAttributes redirectAttributes) {
        labItemService.save(labItem);
        redirectAttributes.addFlashAttribute("successMessage", "检验项目 '" + labItem.getName() + "' 修改成功！");
        return "redirect:/labitem/list";
    }

    /**
     * 删除检验项目
     */
    @GetMapping("/delete/{id}")
    public String deleteLabItem(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            labItemService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "检验项目ID为 " + id + " 的信息删除成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "删除失败，可能该项目正在被使用。");
        }
        return "redirect:/labitem/list";
    }
}