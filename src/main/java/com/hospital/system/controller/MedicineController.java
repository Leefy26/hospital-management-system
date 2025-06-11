package com.hospital.system.controller;

import com.hospital.system.entity.Medicine;
import com.hospital.system.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    /**
     * 显示所有药品列表
     */
    @GetMapping("/list")
    public String listMedicines(Model model) {
        List<Medicine> medicineList = medicineService.findAll();
        model.addAttribute("medicineList", medicineList);
        return "medicine_list";
    }

    /**
     * 显示新增药品的表单页面
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        model.addAttribute("action", "add");
        return "medicine_form";
    }

    /**
     * 处理新增药品的表单提交
     */
    @PostMapping("/add")
    public String addMedicine(@ModelAttribute Medicine medicine, RedirectAttributes redirectAttributes) {
        medicineService.save(medicine);
        redirectAttributes.addFlashAttribute("successMessage", "药品 '" + medicine.getName() + "' 新增成功！");
        return "redirect:/medicine/list";
    }

    /**
     * 显示修改药品的表单页面
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Medicine> medicine = medicineService.findById(id);
        if (medicine.isPresent()) {
            model.addAttribute("medicine", medicine.get());
            model.addAttribute("action", "edit");
            return "medicine_form";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "找不到ID为 " + id + " 的药品。");
        return "redirect:/medicine/list";
    }

    /**
     * 处理修改药品的表单提交
     */
    @PostMapping("/update")
    public String updateMedicine(@ModelAttribute Medicine medicine, RedirectAttributes redirectAttributes) {
        medicineService.save(medicine);
        redirectAttributes.addFlashAttribute("successMessage", "药品 '" + medicine.getName() + "' 修改成功！");
        return "redirect:/medicine/list";
    }

    /**
     * 删除药品
     */
    @GetMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            medicineService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "药品ID为 " + id + " 的信息删除成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "删除失败，可能该药品正在被其他地方使用。");
        }
        return "redirect:/medicine/list";
    }
}