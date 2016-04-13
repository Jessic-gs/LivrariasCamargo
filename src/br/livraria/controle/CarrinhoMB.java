package br.livraria.controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.livraria.dominio.ItemLivro;
import br.livraria.dominio.Livro;
import br.livraria.dominio.Pedido;

@ManagedBean
@SessionScoped
public class CarrinhoMB {
	
	private ItemLivro itemLivroAtual;
	private List<ItemLivro> itens;
	private List<Livro> livros;
	private List<String> imagens;

	
	public CarrinhoMB(){
		//ItemLivroDao ilDao = new ItemLivroDaoImpl();
		itemLivroAtual = new ItemLivro();
		itens = new ArrayList<ItemLivro>();
		/*try {
			itens = ilDao.listaItemLivro();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		livros = carregalista();	
		setImagens(carregaImagens());
	}
	
	public List<String> carregaImagens() {
		List<String> lista = new ArrayList<String>();
		lista.add("/img/livro-jogosvorazes-4.jpg");
		lista.add("/img/guerra-civil-quadrinheiros.jpg");
		return lista;
	}

	public List<Livro> carregalista(){
		List<Livro> lista = new ArrayList<Livro>();
		
		List<String> ilustracao = new ArrayList<String>();
		ilustracao.add("/img/livro-jogosvorazes-4.jpg");
		
		Livro l1 = new Livro();
		l1.setIlustracao(ilustracao);
		l1.setNome("Jogos Vorazes");
		l1.setPreco(20.0f);
		
		List<String> ilustracao2 = new ArrayList<String>();
		ilustracao2.add("/img/guerra-civil-quadrinheiros.jpg");
		
		Livro l2 = new Livro();
		l2.setIlustracao(ilustracao2);
		l2.setNome("Guerra Civil");
		l2.setPreco(20.0f);
		
		lista.add(l1);
		lista.add(l2);
		l2 = new Livro();
		return lista;
	}
	
	public String adiciona( Livro livro ){
		/*ItemLivroDao ilDao = new ItemLivroDaoImpl();
		try {
			ilDao.inserirItemLivro( itemLivroAtual );
			itens = ilDao.listaItemLivro();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		Pedido pedido = new Pedido();
		pedido.setNumero(1);
		
		itemLivroAtual.setLivro(livro);
		itemLivroAtual.setPedido(pedido);
		
		itens.add(itemLivroAtual);
		itemLivroAtual = new ItemLivro();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("carrinho.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Adicionado ao carrinho";
	}
	
	public void atualizar(ItemLivro p){
		/*ItemLivroDao ilDao = new ItemLivroDaoImpl();
		try {
			ilDao.inserirItemLivro(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}
	
	public String editar(ItemLivro il) {
		itemLivroAtual = il;
		return "";
	}
	
	public void soma(ItemLivro item){
		int quantidade;
		itemLivroAtual = item;
		quantidade = item.getQuantidade();
		quantidade++;
		itemLivroAtual.setQuantidade(quantidade);
	}
	
	public void subtrai(ItemLivro item) {
		int quantidade;
		itemLivroAtual = item;
		quantidade = item.getQuantidade();
		quantidade--;
		itemLivroAtual.setQuantidade(quantidade);
		if (itemLivroAtual.getQuantidade() <= 0){
			remover(itemLivroAtual);
		}
	}

	public String remover(ItemLivro il){
		/*ItemLivroDao ilDao = new ItemLivroDaoImpl();
		try {
			ilDao.excluirItemLivro(p);
			itens = ilDao.listaItemLivro();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		itens.remove(il);
		return "Remover";
	}

	public ItemLivro getItemLivroAtual() {
		return itemLivroAtual;
	}

	public void setItemLivroAtual(ItemLivro itemLivroAtual) {
		this.itemLivroAtual = itemLivroAtual;
	}

	public List<ItemLivro> getItens() {
		return itens;
	}

	public void setItens(List<ItemLivro> itens) {
		this.itens = itens;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public List<String> getImagens() {
		return imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}
	
	
	
}
