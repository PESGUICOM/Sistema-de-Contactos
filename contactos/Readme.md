# Creando proyecto Sistema de Contactos
## Spring Initializr
Seleccionamos Maven, Java, version por default y la versión de Java 21.
## Dependencias a agregar
* Spring Web
* Thymeleaf (TEMPLATE ENGINES)
* Spring Data JPA (SQL)
* MySQL Driver (SQL)
* Lombok (DEVELOPER TOOLS)

En el archivo pom.xml están todas las dependencias agregadas y con sus versiones en algunos casos.
## Archivo application.properties
Agregamos la conexión a la base de datos de MySql, creamos la bd (base de datos) si no existe, y proporcionamos los datos
de url, user, password, jpa y también si queremos modificar el puerto (port) de la aplicación.
En la carpeta Resources pegamos el archivo logback-spring.xml, sirve para controlar la información que se ve por consola. 
El nivel "info" y ref="STDOUT" va a la consola estandar.

## Las carpetas static y templates dentro de la carpeta resources
Static es para agregar recursos estáticos como imágenes o archivos para la aplicación y Templates 
para agregar archivos thymeleaf que trabaja con archivos html y se comunica con el controlador de spring.
En la carpeta templates creamos el archivo index.html, incluímos Bootstrap con este código.
## CSS
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" 
rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
## JS
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
## Clase Entity (Entidad)
Creamos el package modelo y dentro la clase Contacto.
Implementamos todos los métodos:

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

Dentro de la clase
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)

## Clase Repositorio (Spring Data)
Extendemos la interface a JpaRepository<Contacto, Integer>
Proporcionamos la clase y la llave primaria.
Para poder interactuar con la base de datos y tener acceso a los métodos para recuperar, modificar, eliminar, agregar,
etc., todos los contactos.

## Clase Servicio (Spring)
Creamos dos clases en distintos archivos una interface IContactoServicio que va a tener todos los métodos: listar,
buscar, modificar, eliminar, etc.
La clase ContactoServicio que implementa(implements) de la interface IContactoServicio, e implementamos todos los métodos con @Override.
Agregar la anotación antes de la clase para poder implementar los elementos y trabajar en la fábrica de Spring. @Service
## Diagrama Arquitectura
La capa de Servicio va a necesitar a la capa de Repositorio para recuperar la información de la capa de Entidad, 
de la base de datos. Entonces la fábrica de Spring, inyecta un objeto de tipo Repositorio en la capa de Servicio. 
Agregamos en la clase ContactoServicio: 
 @Autowired
 private ContactoRepositorio contactoRepositorio;
Entonces de manera automática va a agregar una instancia de esta clase de repositorio y se obtiene acceso sin problemas.
## Patrón de diseño MVC (Modelo-Vista-Controlador)
El cliente que tiene un navegador web y hace una petición hacia nuestro servidor, en este caso a nuestra ip local 
http://localhost:8080/ y la url es "/" (diagonal), la url inicial de nuestra aplicación. Esta es la parte del cliente 
el navegador web.
Hace una petición a nuestro servidor, en este caso es el servidor de Tomcat.
La url es una diagonal "/" y se hace una petición hacia la clase controlador, se hace una petición de tipo Get hacia 
el servidor, entonces agregamos un método que va a procesar esta petición y a través de este método, nos conectamos con 
la clase de servicio, finalmente la clase de repositorio,  y obtenemos la información de la base de datos según necesitemos.
Posteriormente nuestra clase entidad, regresamos nuestro tipos de objetos,  y nuevamente comienza el flujo desde la
base de datos, posteriormente del repositorio, la capa de servicio y este regresa la información necesaria al controlador.
Esto se lo conoce como el modelo de nuestra aplicación. Cuando el controlador tiene la información lo redirecciona hacia 
la vista, en este caso el index.html, y una vez que Thymeleaf recibe la petición regresa al cliente y el navegador web 
despliega la información.
## Agregar menú de navegación con Bootstrap
<div class="container">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">Sistema de Contactos</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" 
aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">Inicio</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/agregar">Agregar Contacto</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
</div>
Cambiamos los textos del menú de navegación, primero modificamos el url en href="/", y luego el texto y los elementos 
de navegación. Cambiamos el href="/" e Inicio, en href="/agregar" y su respectivo texto Agregar Contacto.
## Aplicando clases (class) a la tabla (table) de Bootstrap
Todo envuelto en un <div class="container">, 
<table class="table table-striped table-hover align-middle table-bordered">
                 <thead class="table-dark text-center">
Dar estilo a la tabla con table-striped para que cada registro tenga un color distinto, table-hover para cambiar el color
mientras pasamos con el mouse por encima de los registros de la tabla, alinear los elementos vertical en el centro con
align-middle y table-bordered para separar y tener una división entre cada uno de los registros.
Aplicamos en thead (table head) table-dark tabla oscura y centrar los textos de los cabeceros de las columnas.
## Elementos de las columnas
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Nombre y Apellido</th>
      <th scope="col">Celular</th>
      <th scope="col">Email</th>
     </tr>
## Crear un paquete (package) controlador
primero agregamos @Controller para importar el framework de Spring
Creando el controlador (package) con dos clases ContactoControlador creamos una variable para mandar información al 
logger de la aplicación.
         private static final Logger logger =  LoggerFactory.getLogger(ContactoControlador.class); //parámetro ContactoControlador.class
Importamos org.slf4j.Logger;
import org.slf4j.LoggerFactory;
Definimos atributo, primero agregar @Autowired para que se inyecte automáticamente el objeto de ContactoServicio. 
Definir el atributo y tenemos acceso directo a la capa de servicio y comunicación directa con la base de datos.
## Procesar la petición 
De tipo URL con la anotación GetMapping e indicamos la url que vamos a procesar.
@GetMapping("/")
y definir el método de iniciar con el atributo o objeto ModelMap modelo para recibir o compartir información. 
## Importar Thymeleaf para integrar en la página web
En el archivo index.html importamos el archivo xmls.
xmlns = xml name space (nombre de espacio xml)
<html lang="en" xmlns:th="http://www.thymeleaf.org">
Los elementos de tipos tr de nuestro objeto, indicamos las siguientes etiquetas, th y la función each para cada objeto
que tengamos en la lista de contactos. Pasamos la llave de contactos y devuelve la lista de contactos.
<tr th:each="contacto: ${contactos}">
Para obtener información de la base de datos y poder desplegar en cada uno de los elementos. También puede ser td o th 
según lo que utilice Bootstrap.
           <th th:text="${contacto.idContacto}" scope="row"></th>
           <td th:text="${contacto.nombre}"></td>
           <td th:text="${contacto.celular}"></td>
           <td th:text="${contacto.email}"></td>
## Templates
Dentro de la carpeta resources, templates, creamos un directorio: fragmentos, dentro de él los archivos html, cabecero.html,
navegacion.html, pie-pagina.html. 
En el archivo pie-pagina incluímos un div y dentro el script de bootstrap js para compartir con todas las páginas. 
Esto para que no tome el div y si el script: 
 <div th:fragment="pie-pagina-seccion" th:remove="tag">
 Para compartir la información del archivo pie-pagina con el index.html, en este escribimos:  
<div th:replace="~{fragmentos/pie-pagina::pie-pagina-seccion}"></div>
 
 Archivo cabecero incluímos del index.html el link de bootstrap y el título para todas las páginas. 
 <head th:fragment="cabecero-seccion(titulo)">
    <meta charset="UTF-8">
    <title th:text="${titulo}"></title>
En el archivo index reemplazar el head con la etiqueta th:replace con el símbolo de tilde y entre llaves especificamos la 
ruta donde se encuentra  la página y agregamos doble dos puntos y el nombre de la sección, y el título de nuestra página Inicio.
<head th:replace="~{fragmentos/cabecero::cabecero-seccion(titulo='Inicio')}">

Archivo navegacion agregar:
<div th:fragment="navegacion-seccion" th:remove="tag">
y en el archivo index reemplazamos con:
<div th:replace="~{fragmentos/navegacion::navegacion-seccion}"></div>

##Formulario Agregar
En la clase ContactoControlador agregamos un @GetMapping("/agregar") con la url /agregar. 
public String mostrarAgregar(){
        return "agregar"; //agregar.html
    }
Crear el archivo agregar.html en la carpeta templates y agregamos el cabecero y el pie-pagina:
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head th:replace="~{fragmentos/cabecero::cabecero-seccion(titulo='Agregar')}">
</head>
<body>
     <!--Div es del menú de navegación-->
     <div th:replace="~{fragmentos/navegacion::navegacion-seccion}"/>
     
      <div class="container">
         <div class="container text-center" style="margin:30px">
             <h3>Agregar Contacto</h3>
         </div>
     </div>

     <div th:replace="~{fragmentos/pie-pagina::pie-pagina-seccion}"/>
</body>
</html>
Y agregamos dentro de div el texto centrado con un margen en todas direcciones de 30 pixeles.
Agregar formulario de Bootstrap y configurar los elementos: nombre, celular, email.
  <form>
     <div class="mb-3">
        <label for="nombre" class="form-label">Nombre-Apellido</label>
         <input type="text" class="form-control" id="nombre" name="nombre">
     </div>
     <div class="mb-3">
         <label for="celular" class="form-label">Celular</label>
         <input type="text" class="form-control" id="celular" name="celular">
     </div>
     <div class="mb-3">
         <label for="email" class="form-label">Email</label>
         <input type="text" class="form-control" id="email" name="email">
     </div>
Agregamos el botón de Agregar dentro de un div con la clase container, con el color amarillo de warning, btn-sm tamaño 
pequeño y un margen 3 a la derecha me-3 (agregar espacio hacia la derecha para que no se pegue con el otro botón.
<button type="submit" class="btn btn-warning btn-sm me-3">Agregar</button>
Y otro botón de un link:
 <a href="/" class="btn btn-danger btn-sm">Regresar</a>
En el archivo agregar.html agregamos el método post al formulario y el url es get.
En el controlador agregamos el método post para poder procesar. Y agregamos un objeto para que lleve la información del 
formulario (modelAttribute).
<form action="/agregar" modelAttribute="contactoForma" method="post">
En la clase ContactoControlador agregamos un método postmapping para procesar una petición post. El atributo contactoForma
es de html y luego utilizamos la variable y el objeto Contacto contacto.
@PostMapping("/agregar")
    public String agregar(@ModelAttribute("contactoForma") Contacto contacto){
        logger.info("Contacto a agregar: " + contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/"; //redirigimos al controlador el path "/"
    }
Cuando guardamos el contacto si el idContacto es null se hace un INSERT, si es diferente a null se hace un UPDATE.
## Agregammos otra columna para los botones de editar y eliminar, en el index.html.
 <th scope="col"></th>
 Y el td para agregar los botones.
 <td class="text-center">
        <div class="container">
        <a th:href="@{/editar/{id}(id=${contacto.idContacto})}" 
        class="btn btn-warning btn-sm me-3">Editar</a>
        <a th:href="@{/eliminar/{id}(id=${contacto.idContacto})}" 
        class="btn btn-danger btn-sm me-3">Eliminar</a>
        </div>
 </td>
Así se generan los url con el parámetro editar, y el parámetro id con el valor del objeto, iteramos y enviamos el valor a idContacto.
<a th:href="@{/editar/{id}(id=${contacto.idContacto})}" 

## Creamos controlador del botón editar
El método Getmapping para procesar la petición url "/editar/{id}" con el parametro id.

## Archivo Editar
Copiamos el archivo agregar.html y lo pegamos dentro de la carpeta templates, modificamos el título por Editar y el botón.
<head th:replace="~{fragmentos/cabecero::cabecero-seccion(titulo='Editar')}">
<form action="/editar" modelAttribute="contactoForma" method="post">
<button type="submit" class="btn btn-warning btn-sm me-3">Editar</button>
Para compartir el objeto modelo desde el controlador.(th:object="${contacto}").
<form action="/editar" th:object="${contacto}" method="post">

## Objeto y atributo del objeto
th:object="${contacto}" //objeto
<form action="/editar" th:object="${contacto}" method="post">
th:field="*{nombre}"  //atributo nombre(asterisco para referenciar el objeto)
<input type="text" class="form-control" id="nombre" name="nombre" required="true" th:field="*{nombre}">
Y hacemos lo mismo para los otros campos de celular y email.

## Agregando elemento oculto al formulario (form)
Para volver a enviar la información del id al servidor. Un elemento de tipo input el tipo oculto (hidden) para que no 
se muestre y se vuelva enviar el valor al servidor con field. 
 <input type="hidden" th:field="*{idContacto}"/>
 # Dato HTTP
 HTTP es un protocolo que no guarda el estado (stateless) o sin memoria.
 Si no se agrega la información idContacto cuando queremos enviar de nueva cuenta este formulario hacia el servidor, 
 cuando hacemos submit de este formulario se toma todos los elementos de tipos input se van asociar a este tipo de elemento
 contacto, por lo tanto si no vaciamos este elemento idContacto, no va hacer parte de este objeto. Y en lugar de editar 
 estaríamos haciendo una inserción. 
 
 ## Caso Editar
 Crear el método PostMapping para procesar la petición con la url "/editar", creamos el método editar con el @ModelAttribute
 el parametro contacto del html, en Java pasamos el objeto Contacto contacto. Para guardar el elemento a editar con el método
 contactoServicio y redireccionamos al path "/" iniciar.
 ## Caso Eliminar
  @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") int idContacto){
        Contacto contacto = new Contacto();
        contacto.setIdContacto(idContacto);
        contactoServicio.eliminarContacto(contacto);
        return "redirect:/"; //redireccionando al controlador path "/" de inicio

Es parecido al de editar.