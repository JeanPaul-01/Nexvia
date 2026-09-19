package com.nexvia.nexvia.config;

import com.nexvia.nexvia.entity.Categoria;
import com.nexvia.nexvia.entity.Responsable;
import com.nexvia.nexvia.entity.Usuario;
import com.nexvia.nexvia.repository.CategoriaRepository;
import com.nexvia.nexvia.repository.ResponsableRepository;
import com.nexvia.nexvia.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ResponsableRepository responsableRepository;

    public DataInitializer(UsuarioRepository usuarioRepository,
                           CategoriaRepository categoriaRepository,
                           ResponsableRepository responsableRepository) {
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.responsableRepository = responsableRepository;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            usuarioRepository.save(Usuario.builder()
                    .nombres("Jean Paul")
                    .apellidos("Velasquez")
                    .correo("jean.nexvia@mail.com")
                    .telefono("999999999")
                    .build());
        }

        if (categoriaRepository.count() == 0) {
            categoriaRepository.save(Categoria.builder()
                    .nombre("Software")
                    .descripcion("Problemas relacionados con aplicaciones o sistemas")
                    .build());
            categoriaRepository.save(Categoria.builder()
                    .nombre("Hardware")
                    .descripcion("Problemas relacionados con equipos físicos")
                    .build());
            categoriaRepository.save(Categoria.builder()
                    .nombre("Redes")
                    .descripcion("Problemas de conexión o infraestructura de red")
                    .build());
        }

        if (responsableRepository.count() == 0) {
            responsableRepository.save(Responsable.builder()
                    .nombre("Técnico Nivel 1")
                    .especialidad("Soporte general")
                    .disponible(true)
                    .build());
            responsableRepository.save(Responsable.builder()
                    .nombre("Supervisor TI")
                    .especialidad("Escalamiento")
                    .disponible(true)
                    .build());
        }
    }
}
