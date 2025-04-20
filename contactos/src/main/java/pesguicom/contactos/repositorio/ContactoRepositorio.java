package pesguicom.contactos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import pesguicom.contactos.modelo.Contacto;

public interface ContactoRepositorio  extends JpaRepository<Contacto, Integer> {
}
