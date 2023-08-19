
public class Notas {
    private String titulo;
    private String conteudo;

    public Notas(String titulo, String content) {
        this.titulo = titulo;
        this.conteudo = content;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContent() {
        return conteudo;
    }

    @Override
    public String toString() {
        return titulo;
    }
}