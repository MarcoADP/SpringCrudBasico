package com.example.usuariocrud.service;

import com.example.usuariocrud.entity.Usuario;
import com.example.usuariocrud.params.UsuarioParams;
import com.example.usuariocrud.repository.UsuarioRepository;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;
import javax.transaction.Transactional;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class UsuarioService {
    
    final
    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario create(UsuarioParams params, MultipartFile file) throws IOException {
        Usuario usuario = new Usuario(params.getCodigo(), params.getNome(), params.getDataNascimento());
        if (file != null) {
            String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            usuario.setFoto(filename);
            StorageService.saveFileLocal(file, usuario.getFoto());
        }

        return save(usuario);
    }

    public Usuario createData(UsuarioParams params) {
        Usuario usuario = new Usuario(params.getCodigo(), params.getNome(), params.getDataNascimento());
        return save(usuario);
    }

    public Usuario update(Integer id, UsuarioParams params,  MultipartFile file) throws IOException {
        Usuario usuario = findByIdOrThrow(id);
        usuario.update(params.getCodigo(), params.getNome(), params.getDataNascimento());

        if (file != null) {
            if (usuario.getFoto() == null) {
                usuario.setFoto(UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
            }
            StorageService.saveFileLocal(file, usuario.getFoto());
        }

        return save(usuario);
    }

    public Usuario updateData(Integer id, UsuarioParams params) {
        Usuario usuario = findByIdOrThrow(id);
        usuario.update(params.getCodigo(), params.getNome(), params.getDataNascimento());
        return save(usuario);
    }

    public Usuario updateFoto(Integer id, MultipartFile file) throws IOException {
        Usuario usuario = findByIdOrThrow(id);

        if (file != null) {
            if (usuario.getFoto() == null) {
                usuario.setFoto(UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
            }
            StorageService.saveFileLocal(file, usuario.getFoto());
        }

        return save(usuario);
    }

    public void delete(Integer id) {
        Usuario usuario = findByIdOrThrow(id);
        usuarioRepository.delete(usuario);
    }

    public Usuario remove(Integer id) {
        Usuario usuario = findByIdOrThrow(id);
        usuario.remove();
        return save(usuario);
    }

    public Usuario restore(Integer id) {
        Usuario usuario = findByIdOrThrow(id);
        usuario.restore();
        return save(usuario);
    }

    public Usuario findByIdOrThrow(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Usuario não encontrado"));
    }

    public Iterable<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findByCodigo(String codigo) {
        return usuarioRepository.findFirstByCodigo(codigo);
    }
}
