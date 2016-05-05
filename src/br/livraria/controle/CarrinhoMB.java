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
		pedido.setValorTotal(0.0f);
	}
	
	public String confirmaLivro( Livro livro ){
		
		itemLivroAtual.setLivro(livro);
		itemLivroAtual.setQuantidade(1);
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("carrinho.xhtml?confirma");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String adiciona( ItemLivro il ){
		/*ItemLivroDao ilDao = new ItemLivroDaoImpl();
		try {
			ilDao.inserirItemLivro( itemLivroAtual );
			itens = ilDao.listaItemLivro();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		float valorTotal = pedido.getValorTotal() + (il.getLivro().getPreco() * il.getQuantidade());
		pedido.setValorTotal(valorTotal);
		pedido.setNumero(1);
		
		il.setPedido(pedido);
				
		itens.add(il);
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}
