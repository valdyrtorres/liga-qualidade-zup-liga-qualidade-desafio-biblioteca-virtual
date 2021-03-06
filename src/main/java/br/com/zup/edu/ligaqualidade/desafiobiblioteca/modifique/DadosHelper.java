package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;

import java.util.Set;

public class DadosHelper {

    private static final String ERROR_MESSAGE = "Não encontrei esse motherfucker";

    private DadosHelper() {}

    static DadosUsuario buscaUsuario(int idUsuario, Set<DadosUsuario> usuarios) {
        return usuarios.stream().filter(usuario -> usuario.idUsuario == idUsuario).findFirst()
                .orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
    }

    static DadosExemplar buscaExemplar(int idLivro, Set<DadosExemplar> exemplares) {
        return exemplares.stream().filter(exemplar -> exemplar.idLivro == idLivro).findFirst()
                .orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
    }
}
