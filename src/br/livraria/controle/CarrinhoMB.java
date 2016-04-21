package br.livraria.controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.text.html.ListView;

import br.livraria.dominio.ItemLivro;
import br.livraria.dominio.Livro;
import br.livraria.dominio.Pedido;

@ManagedBean
@SessionScoped
public class CarrinhoMB {
	
	private ItemLivro itemLivroAtual;
	private Pedido pedido;
	private List<ItemLivro> itens;
	private List<Livro> livros; // Sera Apagado
	
	public CarrinhoMB(){
		//ItemLivroDao ilDao = new ItemLivroDaoImpl();
		itemLivroAtual = new ItemLivro();
		itens = new ArrayList<ItemLivro>();
		pedido = new Pedido();
		/*try {
			itens = ilDao.listaItemLivro();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		livros = carregalista();
		pedido.setValorTotal(0.0f);
	}
	
	//**************************************************************
	// Sera apagado
	// Só o fiz aqui para testar o carrinho
	// Esse metodo não é aqui é no LivroMB e também não é assim
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
	//**************************************************************
	
	public String confirmaLivro( Livro livro ){
		pedido.setNumero(1);
		
		itemLivroAtual = new ItemLivro();
		itemLivroAtual.setLivro(livro);
		itemLivroAtual.setPedido(pedido);
		if (itemLivroAtual.getQuantidade() != 1){
			itemLivroAtual.setQuantidade(1);
		}
		
		return "";
	}
	
	public String adiciona( Livro livro ){
		/*ItemLivroDao ilDao = new ItemLivroDaoImpl();
		try {
			ilDao.inserirItemLivro( itemLivroAtual );
			itens = ilDao.listaItemLivro();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		float valorTotal = pedido.getValorTotal()+livro.getPreco();
		pedido.setNumero(1);
		pedido.setValorTotal(valorTotal);
		
		itemLivroAtual = new ItemLivro();
		itemLivroAtual.setLivro(livro);
		itemLivroAtual.setPedido(pedido);
		if (itemLivroAtual.getQuantidade() != 1){
			itemLivroAtual.setQuantidade(1);
		}
				
		itens.add(itemLivroAtual);
		itemLivroAtual = new ItemLivro();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("carrinho.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
	
	public void soma(ItemLivro item){
		int quantidade;
		itemLivroAtual = item;
		quantidade = item.getQuantidade();
		quantidade++;
		itemLivroAtual.setQuantidade(quantidade);
		float valorTotal = pedido.getValorTotal()+itemLivroAtual.getLivro().getPreco();
		pedido.setValorTotal(valorTotal);
	}
	
	public void subtrai(ItemLivro li) {
		int quantidade;
		quantidade = li.getQuantidade();
		quantidade--;
		li.setQuantidade(quantidade);
		if (li.getQuantidade() <= 0){
			remover(li);
		}
		float valorTotal = pedido.getValorTotal()-li.getLivro().getPreco();
		pedido.setValorTotal(valorTotal);
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
		float valorTotal = pedido.getValorTotal()-(il.getLivro().getPreco()*il.getQuantidade());
		pedido.setValorTotal(valorTotal);
		return "";
	}
	
	public String limpaCarrinho(){
		/*ItemLivroDao ilDao = new ItemLivroDaoImpl();
		try {
			ilDao.excluirItemLivro(p);
			itens = ilDao.listaItemLivro();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		itens = new ArrayList<ItemLivro>();
		pedido.setValorTotal(0.0f);
		return "";
	}
	
	public String concluirPedido(List<ItemLivro> listaIL){
		return "";
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
	
	//**************************************************************
	// Sera apagado
	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	//**************************************************************

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}
