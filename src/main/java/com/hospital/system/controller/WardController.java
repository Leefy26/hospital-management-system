package com.hospital.system.controller;

import com.hospital.system.entity.Ward;
import com.hospital.system.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ward")
public class WardController {

    @Autowired
    private WardService wardService;

    @GetMapping("/list")
    public String listWards(Model model) {
        List<Ward> wardList = wardService.findAll();
        model.addAttribute("wardList", wardList);
        return "ward_list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("ward", new Ward());
        model.addAttribute("action", "add");
        return "ward_form";
    }

    @PostMapping("/add")
    public String addWard(@ModelAttribute Ward ward) {
        wardService.save(ward);
        return "redirect:/ward/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Ward> ward = wardService.findById(id);
        if (ward.isPresent()) {
            model.addAttribute("ward", ward.get());
            model.addAttribute("action", "edit");
            return "ward_form";
        }
        return "redirect:/ward/list";
    }

    @PostMapping("/update")
    public String updateWard(@ModelAttribute Ward ward) {
        wardService.save(ward);
        return "redirect:/ward/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteWard(@PathVariable Integer id) {
        wardService.deleteById(id);
        return "redirect:/ward/list";
    }
}