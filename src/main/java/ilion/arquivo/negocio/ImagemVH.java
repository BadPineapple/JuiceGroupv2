package ilion.arquivo.negocio;

import java.util.ArrayList;
import java.util.List;

public class ImagemVH {

	private String url;

	private String url2;
	
	private String title;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ImagemVH [url=" + url + ", url2=" + url2 + ", title=" + title + "]";
	}

	public static ImagemVH from(Arquivo a) {
		
		ImagemVH imagemVH = new ImagemVH();
		
		imagemVH.url = a.getUrl();
		imagemVH.url2 = a.getUrl2();
		imagemVH.title = a.getTitle();
		
		return imagemVH;
	}

	public static List<ImagemVH> from(List<Arquivo> arquivos) {
		
		List<ImagemVH> imagens = new ArrayList<>();
		
		for (Arquivo arquivo : arquivos) {
			
			ImagemVH imagemVH = ImagemVH.from(arquivo);
			imagens.add(imagemVH);
			
		}
		
		return imagens;
	}
	
}