# Sistema de Biblioteca Digital

Este projeto implementa um sistema de biblioteca digital usando Quarkus, o Supersonic Subatomic Java Framework.

## 🚀 **Funcionalidades Implementadas**

### **Interface Web (JSF)**
- Listagem de livros, autores e empréstimos
- Cadastro de novos autores
- Estatísticas da biblioteca

### **API REST (JAX-RS)**
- `GET /api/livros` - Lista todos os livros
- `GET /api/autores` - Lista todos os autores
- `GET /api/estatisticas` - Retorna estatísticas em JSON

## 📚 **O que é JAX-RS?**

**JAX-RS** (Java API for RESTful Web Services) é uma especificação Java que facilita a criação de APIs REST. É como um "tradutor" que converte requisições HTTP em chamadas de métodos Java.

### **Principais anotações JAX-RS:**

- **`@Path("/api/livros")`** - Define a URL do endpoint
- **`@GET`** - Especifica que aceita requisições GET
- **`@Produces(MediaType.APPLICATION_JSON)`** - Define que retorna JSON
- **`@PathParam("id")`** - Captura parâmetros da URL
- **`@QueryParam("nome")`** - Captura parâmetros de query

### **Exemplo prático:**
```java
@Path("/api/livros")
@Produces(MediaType.APPLICATION_JSON)
public class LivroBean {
    
    @GET
    public List<Livro> listarTodosLivros() {
        return service.listarTodosLivros();
    }
    
    @GET
    @Path("/{id}")
    public Livro buscarPorId(@PathParam("id") Long id) {
        return service.buscarLivroPorId(id);
    }
}
```

### **Como funciona:**
1. Cliente faz requisição: `GET http://localhost:8080/api/livros`
2. JAX-RS identifica o método correto pelo `@Path` e `@GET`
3. Executa o método Java
4. Converte o resultado para JSON automaticamente
5. Retorna a resposta HTTP

### **Vantagens do JAX-RS:**
- ✅ **Simples:** Apenas anotações, sem XML
- ✅ **Padrão:** Especificação oficial Java
- ✅ **Automático:** Conversão JSON automática
- ✅ **Flexível:** Suporta diferentes formatos (JSON, XML, etc.)

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/biblioteca-final-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- RESTEasy Classic ([guide](https://quarkus.io/guides/resteasy)): REST endpoint framework implementing Jakarta REST and more

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
