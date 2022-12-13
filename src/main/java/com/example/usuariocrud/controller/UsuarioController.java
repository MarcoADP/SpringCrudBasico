package com.example.usuariocrud.controller;

import com.example.usuariocrud.entity.Usuario;
import com.example.usuariocrud.params.UsuarioParams;
import com.example.usuariocrud.service.UsuarioService;
import java.io.IOException;
import java.io.InputStream;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path="/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // CREATE
    @PostMapping( consumes = { "multipart/form-data" })
    public @ResponseBody Usuario create(@RequestPart UsuarioParams params, @RequestPart MultipartFile foto) throws IOException {
        return usuarioService.create(params, foto);
    }

    @PostMapping("/data")
    public @ResponseBody Usuario createData(@Valid @RequestBody UsuarioParams params) {
        return usuarioService.createData(params);
    }

    // UPDATE
    @PutMapping(value="{id}", consumes = { "multipart/form-data" })
    public @ResponseBody Usuario update(@PathVariable Integer id, @RequestPart UsuarioParams params, @RequestPart MultipartFile foto) throws IOException {
        return usuarioService.update(id, params, foto);
    }

    @PutMapping("/data/{id}")
    public @ResponseBody Usuario updateData(@PathVariable Integer id, @RequestBody UsuarioParams params) {
        return usuarioService.updateData(id, params);
    }

    @PutMapping("/foto/{id}")
    public @ResponseBody Usuario updateFoto(@PathVariable Integer id, @RequestPart MultipartFile foto) throws IOException {
        return usuarioService.updateFoto(id, foto);
    }

    // RECOVER
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Usuario> getAll() {
        return usuarioService.findAll();
    }

    @GetMapping(path="/foto/{id}" , produces="application/octet-stream")
    public ResponseEntity getFoto(@PathVariable Integer id) {

        Usuario usuario = usuarioService.findByIdOrThrow(id);
        InputStream in = usuarioService.getFoto(usuario.getFoto());

        if (in != null) {
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment;filename=" + usuario.getFoto())
                    .body(new InputStreamResource(in));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public @ResponseBody Usuario get(@PathVariable Integer id) {
        return usuarioService.findByIdOrThrow(id);
    }

    @GetMapping("/codigo/{codigo}")
    public @ResponseBody Usuario get(@PathVariable String codigo) {
        return usuarioService.findByCodigo(codigo);
    }

    // DELETE
    @DeleteMapping("{id}")
    public @ResponseBody void delete(@PathVariable Integer id) {
        usuarioService.delete(id);
    }

    @PutMapping("/remove/{id}")
    public @ResponseBody Usuario remove(@PathVariable Integer id) {
        return usuarioService.remove(id);
    }

    @PutMapping("/restore/{id}")
    public @ResponseBody Usuario restore(@PathVariable Integer id) {
        return usuarioService.restore(id);
    }

}
