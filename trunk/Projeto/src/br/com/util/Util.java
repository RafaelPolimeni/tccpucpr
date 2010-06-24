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
	 * Responsável por recuperar um objeto da sessão
	 * 
	 * @param String
	 *            id - Id do objeto na sessão
	 * @return Object - objeto na sessão or null caso não haja na sessão
	 */
	public static Object getFromSession(String id) {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		return sessao.getAttribute(id);
	}

	/**
	 * Responsável por verificar se uma String é nula ou vazia
	 * 
	 * @param String
	 *            str - String
	 * @return boolean - true se for vazia ou nula e false se não for
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0 ? true : false;
	}

	/**
	 * Responsável por verificar se uma List é nula ou vazia
	 * 
	 * @param List
	 *            lista
	 * @return boolean - true se for vazia ou nula e false se não for
	 */
	public static boolean isEmpty(List lista) {
		return lista == null || lista.size() == 0 ? true : false;
	}

	/**
	 * A partir da extensão do arquivo recupera o tipo: IMG, DOC, PDF
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
		return text.replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e").replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùü]", "u")
				.replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍÏ]", "I").replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜ]", "U").replace(
						'ç', 'c').replace('Ç', 'C').replace('ñ', 'n').replace('Ñ', 'N');
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
					"[%]", "[@]", "[ª]", "[º]", "\\\\", ">", "<", "[}]", "[{]", "[|]", "\\^", "\\[", "\\]", "&", "-", "~" };

			for (int i = 0; i < characters.length; i++) {
				value = value.replaceAll(characters[i], "");
			}
		}
		return value;
	}

	/**
	 * Responsável por gravar um arquivo. Caso já exista um arquivo com o mesmo
	 * nome é acrescentado "_X" no final do nome do arquivo, onde X é um
	 * contador de 1 a N.
	 * 
	 * @param String
	 *            dir - Diretório do arquivo
	 * @param File
	 *            file - novo arquivo que será gravado
	 * @param UploadItem
	 *            uploadedItem - Arquivo que foi carregado pelo usuário
	 * @return File - Arquivo gravado com nome atualizado, caso já houvesse um
	 *         arquivo com o mesmo nome no diretório
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
	 * Modifica o nome do arquivo a ser gravado até encontrar um que não exista
	 * no diretório. Usa o padrão nomeDoArquivo + "_X" onde X é um contador.
	 * 
	 * @param String
	 *            dir - Diretório do arquivo
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
