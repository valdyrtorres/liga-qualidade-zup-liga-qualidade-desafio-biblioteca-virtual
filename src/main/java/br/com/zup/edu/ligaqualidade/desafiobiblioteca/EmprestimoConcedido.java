package br.com.zup.edu.ligaqualidade.desafiobiblioteca;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;

public class EmprestimoConcedido {

	// id do emprestimo referenciado no PedidoEmprestimo
	public final int idUsuario;
	public final int idExemplar;
	// data em função do tempo de emprestimo
	public final LocalDate dataPrevistaDevolucao;
	// instante da devolução
	private Instant momentoDevolucao;

	/**
	 * 
	 * @param idUsuario id referente ao {@link DadosUsuario}
	 * @param idExemplar id referente ao {@link DadosExemplar}
	 * @param dataPrevistaDevolucao data prevista para devolução em função do número de dias
	 */
	public EmprestimoConcedido(int idUsuario,int idExemplar ,LocalDate dataPrevistaDevolucao) {
		super();
		this.idUsuario = idUsuario;
		this.idExemplar = idExemplar;
		this.dataPrevistaDevolucao = dataPrevistaDevolucao;
	}

	// TODO FAZER A DEVOLUÇÃO
	public void registraDevolucao() {
		this.momentoDevolucao = Instant.now();
	}
	
	
	public Optional<Instant> getMomentoDevolucao(){
		return Optional.ofNullable(this.momentoDevolucao);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// CDD 2 pontos TERNÁRIO
		result = prime * result + ((dataPrevistaDevolucao == null) ? 0
				: dataPrevistaDevolucao.hashCode());
		result = prime * result + idExemplar;
		result = prime * result + idUsuario;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// CDD 1 ponto
		if (this == obj)
			return true;
		// CDD 1 ponto
		if (obj == null)
			return false;
		// CDD 1 ponto
		if (getClass() != obj.getClass())
			return false;
		// CDD 1 ponto
		EmprestimoConcedido other = (EmprestimoConcedido) obj;
		// CDD 1 ponto
		if (dataPrevistaDevolucao == null) {
			// CDD 1 ponto
			if (other.dataPrevistaDevolucao != null)
				return false;
			// CDD 1 ponto
		} else if (!dataPrevistaDevolucao.equals(other.dataPrevistaDevolucao))
			return false;
		// CDD 1 ponto
		if (idExemplar != other.idExemplar)
			return false;
		// CDD 1 ponto
		if (idUsuario != other.idUsuario)
			return false;
		return true;
	}
	
// CDD 11 > 7. Pela métrica recomenda-se fatiar essa classe

}
