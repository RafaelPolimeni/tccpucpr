package br.com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.richfaces.model.UploadItem;

import br.com.model.Arquivo;

public class Util {

	public static UIComponent findComponent(UIComponent parent, String id) {
		if (id.equals(parent.getId())) {
			return parent;
		}
		Iterator<UIComponent> kids = parent.getFacetsAndChildren();
		while (kids.hasNext()) {
			UIComponent kid = kids.next();
			UIComponent found = findComponent(kid, id);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	/**
	 * Respons�vel por recuperar um objeto da sess�o
	 * 
	 * @param String
	 *            id - Id do objeto na sess�o
	 * @return Object - objeto na sess�o or null caso n�o haja na sess�o
	 */
	public static Object getFromSession(String id) {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		return sessao.getAttribute(id);
	}

	/**
	 * Respons�vel por verificar se uma String � nula ou vazia
	 * 
	 * @param String
	 *            str - String
	 * @return boolean - true se for vazia ou nula e false se n�o for
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0 ? true : false;
	}

	/**
	 * Respons�vel por verificar se uma List � nula ou vazia
	 * 
	 * @param List
	 *            lista
	 * @return boolean - true se for vazia ou nula e false se n�o for
	 */
	public static boolean isEmpty(List lista) {
		return lista == null || lista.size() == 0 ? true : false;
	}

	/**
	 * A partir da extens�o do arquivo recupera o tipo: IMG, DOC, PDF
	 * 
	 * @param String
	 *            nomeArquivo - nome do arquivo
	 * @return Integer - Tipo do arquivo
	 */
	public static Integer getTipoArquivo(String nomeArquivo) {
		if (nomeArquivo.endsWith(".jpg") || nomeArquivo.endsWith(".jpeg") || nomeArquivo.endsWith(".bmp") || nomeArquivo.endsWith(".png")) {
			return Arquivo.IMG;
		} else if (nomeArquivo.endsWith(".doc") || nomeArquivo.endsWith(".docx")) {
			return Arquivo.DOC;
		} else if (nomeArquivo.endsWith(".pdf")) {
			return Arquivo.PDF;
		}

		return Arquivo.OUTROS;
	}

	/**
	 * Remove os acentos de uma String
	 * 
	 * @param String
	 *            text - valor
	 * @return String - valor sem acentos.
	 */
	public static String removeAccents(String text) {
		return text.replaceAll("[�����]", "a").replaceAll("[����]", "e").replaceAll("[����]", "i").replaceAll("[�����]", "o").replaceAll("[����]", "u")
				.replaceAll("[�����]", "A").replaceAll("[����]", "E").replaceAll("[����]", "I").replaceAll("[�����]", "O").replaceAll("[����]", "U").replace(
						'�', 'c').replace('�', 'C').replace('�', 'n').replace('�', 'N');
	}

	/**
	 * Remove os caracteres especiais de um String
	 * 
	 * @param String
	 *            value - valor
	 * @return String - valor sem caracteres especiais
	 */
	public static String removeSpecialCharacters(String value) {
		if (value != null) {
			String characters[] = new String[] { "/", "[(]", "[)]", "[.]", ",", ";", "[+]", "[*]", "[!]", "[?]", "[\"]", "[']", "[=]", "[:]", "[#]", "[$]",
					"[%]", "[@]", "[�]", "[�]", "\\\\", ">", "<", "[}]", "[{]", "[|]", "\\^", "\\[", "\\]", "&", "-", "~" };

			for (int i = 0; i < characters.length; i++) {
				value = value.replaceAll(characters[i], "");
			}
		}
		return value;
	}

	/**
	 * Respons�vel por gravar um arquivo. Caso j� exista um arquivo com o mesmo
	 * nome � acrescentado "_X" no final do nome do arquivo, onde X � um
	 * contador de 1 a N.
	 * 
	 * @param String
	 *            dir - Diret�rio do arquivo
	 * @param File
	 *            file - novo arquivo que ser� gravado
	 * @param UploadItem
	 *            uploadedItem - Arquivo que foi carregado pelo usu�rio
	 * @return File - Arquivo gravado com nome atualizado, caso j� houvesse um
	 *         arquivo com o mesmo nome no diret�rio
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public static File saveFile(String dir, File file, UploadItem uploadedItem) throws IOException, Exception {
		OutputStream out = null;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(uploadedItem.getFile());

			if (file.exists()) {
				file = criarNovoNomeArquivo(dir, file);
			}

			file.createNewFile();

			out = new FileOutputStream(file);

			byte buf[] = new byte[1024];
			int len = 0;

			while ((len = inputStream.read(buf)) > 0)
				out.write(buf, 0, len);

			return file;
		} catch (IOException ioe) {
			file.delete();
			throw ioe;
		} catch (Exception e) {
			file.delete();
			throw e;
		} finally {
			try {
				if (out != null)
					out.close();
				if (inputStream != null)
					inputStream.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}

	/**
	 * Modifica o nome do arquivo a ser gravado at� encontrar um que n�o exista
	 * no diret�rio. Usa o padr�o nomeDoArquivo + "_X" onde X � um contador.
	 * 
	 * @param String
	 *            dir - Diret�rio do arquivo
	 * @param File
	 *            file - arquivo
	 * @return File novo arquivo com nome atualizado.
	 */
	public static File criarNovoNomeArquivo(String dir, File file) {
		String nomeArquivo = file.getName().substring(0, file.getName().lastIndexOf("."));

		String extensao = file.getName().substring(file.getName().lastIndexOf("."));

		int contador = 1;

		while (new File(dir + File.separator + nomeArquivo + "_" + contador + extensao).exists()) {
			contador++;
		}

		return new File(dir + File.separator + nomeArquivo + "_" + contador + extensao);
	}
}
