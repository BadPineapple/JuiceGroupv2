package ilion.vitazure.model;

/**
 * Created by afrodite on 8/5/21.
 */
public class PostBlog {
  public String titulo;
  public String link;
  public String imagem;

  public PostBlog (String titulo, String link, String imagem) {
    this.titulo = titulo;
    this.link = link;
    this.imagem = imagem;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getImagem() {
    return imagem;
  }

  public void setImagem(String imagem) {
    this.imagem = imagem;
  }
}
