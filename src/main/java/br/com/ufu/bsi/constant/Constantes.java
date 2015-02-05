package br.com.ufu.bsi.constant;


/**
 * Agrupa as constantes do sistema.
 */
public class Constantes {

	/**
	 * Constantes para as chaves de atributos que s�o colocados na sess�o
	 */
	public static final String CHAVE_USUARIO_LOGADO = "usuarioLogado";
	
	public static final String DIRETORIO_RELATORIO = "/usr/relatorios"; // "/usr/relatorios"; "C:\\"
	
	public static final String DIRETORIO_ARQUIVOS = "/usr/arquivos"; // "/usr/arquivos"; // "C:\\arquivos";
	
	public static final String DIRETORIO_ARQUIVO_IMAGENS = "/var/lib/tomcat7/webapps/ROOT/resources/img/produtos"; // "C:\\arquivos\\imagens"; // "/usr/arquivos/imagens";
	
	public static final Integer TIPO_ARQUIVO_INSCRICAO_ESTADUAL = 1;
	
	public static final Integer TIPO_ARQUIVO_COPIA_CPC_RG = 2;
	
	public static final Integer TIPO_ARQUIVO_INTEGRACAO = 4;
	
	public static final Integer TIPO_ARQUIVO_IMAGEM_PRODUTO = 500;
	
	public static final String SENHA_PADRAO_DO_SISTEMA = "102030";
	
	/**
	 * 7:10 - 1
	 * 8:00 - 2
	 * 8:50 - 3
	 * 9:40 - 4
	 * 10:40 - 5
	 * 11:30 - 6
	 * 
	 * 13:10 - 7
	 * 14:00 - 8
	 * 14:50 - 9
	 * 16:00 - 10
	 * 16:50 - 11
	 * 17:40 - 12
	 * 
	 * 19:00 - 13
	 * 19:50 - 14
	 * 20:50 - 15
	 * 21:40 - 16
	 * 
	 * seg - d1 = 2
	 * ter - d2 = 3
	 * qua - d3 = 4
	 * qui - d4 = 5
	 * sex - d5 = 6
	 */
	public static final String HORARIO_AULA_19HS = "";

}