package com.vedoque.notas.controladores;

import com.vedoque.notas.modelo.Entrada;
import com.vedoque.notas.servicios.ServicioEntradas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        if(!bindingResult.hasErrors()) {
            servicio.save(entrada);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/listaEntradas", method = RequestMethod.GET)
    public String listBooks(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Entrada> entradasPagina = servicio.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("bookPage", entradasPagina);

        int totalPages = entradasPagina.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "listaEntradas.html";
    }

}
