package com.vedoque.notas.controladores;

import com.vedoque.notas.modelo.Entrada;
import com.vedoque.notas.servicios.ServicioEntradas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Principal {
    @Autowired
    ServicioEntradas servicio;

    @GetMapping("/")
    public String principal(Model model){
        model.addAttribute("lista", servicio.findAllOrderByFechaDesc());
        return "index";
    }

    @GetMapping("/crud/entradas/crear")
    public String formGuardar(Model model){
        model.addAttribute("entrada", new Entrada());
        return "crear";
    }

    @PostMapping("/crud/entradas/crear")
    public String guardar(@ModelAttribute("entrada") Entrada entrada, @RequestParam(value = "file", required = false) MultipartFile file, BindingResult bindingResult){
        servicio.save(entrada);
        return "redirect:/";
    }

}
