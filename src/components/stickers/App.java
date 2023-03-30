package components.stickers;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import java.util.List;
import java.util.Map;

import components.ClienteHttp;
import components.JsonParser;

public class App {

  public static void main(String[] args) throws Exception {

    // fazer uma conexão HTTP e buscar os top 250 filmes
    //String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
    String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
    
    var http = new ClienteHttp();
    String json = http.buscaDados(url);

    // extrair só os dados que interessam (titulo, poster, classificação)
    var parser = new JsonParser();
    List<Map<String, String>> listaDeFilmes = parser.parse(json);

    // cria pasta 
    var diretorio = new File("ALURA-STICKERS/figurinhas/filmes");
      diretorio.mkdir();

    // exibir e manipular os dados
    var geradora = new GeradorDeFigurinhas();
    for (Map<String,String> filme : listaDeFilmes) {

        String urlImagem = filme.get("image");
        String titulo = filme.get("title");

        InputStream inputStream = new URL(urlImagem).openStream();
        String nomeArquivo = "ALURA-STICKERS/figurinhas/filmes/" + titulo + ".png";

        geradora.cria(inputStream, nomeArquivo);

        System.out.println(titulo);
        System.out.println();
    }
}
 
}
