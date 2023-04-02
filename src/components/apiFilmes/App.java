package components.apiFilmes;


import java.util.List;
import java.util.Map;

import components.ClienteHttp;
import components.JsonParser;


public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(json);
        System.out.println(listaDeFilmes.size());
        // exibir e manipular os dados 
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.print("\u001b[36m Titulo:\u001b[m ");
             System.out.println(filme.get("title"));
             System.out.print("\u001b[36m Poster:\u001b[m ");
             System.out.println(filme.get("image"));
             System.out.print("\u001b[37m\u001b[46m Classificação:\u001b[m ");
             System.out.println(filme.get("imDbRating"));
             double classificacao = Double.parseDouble(filme.get("imDbRating"));
             int numeroEstrela = (int) classificacao;
             for (int i = 1; i <= numeroEstrela; i++) {
                System.out.print("⭐");
             }  
             System.out.println("\n");
        }
    }
}
