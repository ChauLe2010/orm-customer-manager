package com.codegym.controllers;

import com.codegym.model.Province;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;
    @GetMapping("/provinces")
    private ModelAndView listProvince(){
        Iterable<Province> provinces=provinceService.findAll();
        ModelAndView modelAndView=new ModelAndView("/province/list");
        modelAndView.addObject("provinces",provinces);
        return modelAndView;
    }
    @GetMapping("/province/create")
    private ModelAndView showCreateForm(){
        ModelAndView modelAndView=new ModelAndView("/province/create");
        modelAndView.addObject("province",new Province());
        return modelAndView;
    }
    @PostMapping("/province/create")
    private ModelAndView saveProvince(@ModelAttribute("province")Province province){
        provinceService.save(province);
        ModelAndView modelAndView=new ModelAndView("/province/create");
        modelAndView.addObject("province",new Province());
        modelAndView.addObject("message","New province created successfully");
        return modelAndView;
    }
    @GetMapping("/province/{id}/edit")
    private ModelAndView showEditForm(@PathVariable Long id){
        Province province=provinceService.findById(id);
        if(province!=null){
            ModelAndView modelAndView=new ModelAndView("/province/edit");
            modelAndView.addObject("province",province);
            return modelAndView;
        }else{
            ModelAndView modelAndView=new ModelAndView("error");
            return modelAndView;
        }
    }
    @PostMapping("/province/update")
    public ModelAndView updateProvince(@ModelAttribute("province") Province province){
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        modelAndView.addObject("province", province);
        modelAndView.addObject("message", "Province updated successfully");
        return modelAndView;
    }
    @GetMapping("/province/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Province province=provinceService.findById(id);
        if(province!=null){
            ModelAndView modelAndView=new ModelAndView("/province/delete");
            modelAndView.addObject("province",province);
            return modelAndView;
        }else{
            ModelAndView modelAndView=new ModelAndView("error");
            return modelAndView;
        }
    }
    @PostMapping("/province/delete")
    public String deleteProvince(@ModelAttribute("province") Province province){
        provinceService.remove(province.getId());
        return "redirect:../provinces";
    }

}
