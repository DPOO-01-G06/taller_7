package uniandes.cupi2.almacen.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import uniandes.cupi2.almacen.mundo.Almacen;
import uniandes.cupi2.almacen.mundo.AlmacenException;
import uniandes.cupi2.almacen.mundo.Categoria;
import uniandes.cupi2.almacen.mundo.Marca;
import uniandes.cupi2.almacen.mundo.NodoAlmacen;
import uniandes.cupi2.almacen.mundo.Producto;

public class CategoriaTest {
	
	private Categoria categoria;
	
	private void setupescenario1()
	{
		try {
			Almacen almacen = new Almacen(new File("./data/datos.txt"));
			categoria =almacen.darCategoriaRaiz();
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setupescenario2()
	{
		categoria= new Categoria("11", "lavadoras");
	}
	@Test
	public void darNodos( )
    {
		setupescenario1();
		List nodos = categoria.darNodos();
		if (nodos!=null && nodos.size()>0)
		{
			assertTrue(true);
		}
		else
		{
			fail("debia retornar una lista, no una lista vacia");
		}
    }
	@Test
	public void darNodos2( )
    {
		setupescenario2();
		List nodos = categoria.darNodos();
		if (nodos!=null && nodos.size()==0)
		{
			assertTrue(true);
		}
		else
		{
			fail("debia retornar una lista vacia, pues no se han agregado elementos ");
		}
    }

	@Test
    public void buscarPadre( )
    {
      setupescenario1();
      Categoria cat = categoria.buscarPadre("11");
      if (cat != null)
      {
    	  assertTrue(true);
      }
      else
		{
			fail("debia retornar un nodo");
		}
    }
	@Test
    public void buscarPadre2( )
    {
      setupescenario2();
      Categoria cat = categoria.buscarPadre("1");
      if (cat == null)
      {
    	  assertTrue(true);
      }
      else
		{
			fail("no debia retornar un nodo, pues no tiene padre");
		}
    }
	@Test
    public void buscarNodo( )
    {
		 setupescenario1();
	     NodoAlmacen cat = categoria.buscarNodo("11");
	      if (cat != null)
	      {
	    	  assertTrue(true);
	      }
	      else
			{
				fail("debia retornar un nodo");
			}
    }
	@Test
    public void buscarNodo2( )
    {
		 setupescenario2();
	     NodoAlmacen cat = categoria.buscarNodo("248");
	      if (cat == null)
	      {
	    	  assertTrue(true);
	      }
	      else
			{
				fail("no debia retornar un nodo, ya que no existe");
			}
    }
	@Test
    public void agregarNodo1(  ) 
    {
		setupescenario2();
		try {
			categoria.agregarNodo("11", "Marca", "245", "lg");
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodoAlmacen nod = categoria.buscarNodo("245");
		if (nod!=null)
				assertEquals(nod.darIdentificador(), "245");
		else
			fail("debia de encontrar el nodo creado");
  
    }
	@Test
    public void agregarNodo2( ) 
    {
		setupescenario2();
		try {
			categoria.agregarNodo("203", "Marca", "245", "lg");
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodoAlmacen nod = categoria.buscarNodo("245");
		if (nod==null)
				assertTrue(true);
		else
			fail("no existe un nodo padre, por lo que no debio crearse el nodo");
  
    }

	@Test
    public void eliminarNodo(  )
    {
		setupescenario2();
		try {
			categoria.agregarNodo("11", "Marca", "245", "lg");
			categoria.eliminarNodo("245");
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodoAlmacen nod = categoria.buscarNodo("245");
		if (nod==null)
				assertTrue(true);
		else
			fail("el nodo se elimino, no deberia encontrarse un nodo");
    }
	
	@Test
    public void eliminarNodo2(  )
    {
		setupescenario2();
		try {
			categoria.agregarNodo("11", "Marca", "245", "lg");
			categoria.eliminarNodo("300");
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodoAlmacen nod = categoria.buscarNodo("245");
		if (nod!=null)
				assertTrue(true);
		else
			fail("se elimino un nodo diferente, deberia encontrarse el nodo creado");
    }


	@Test
    public void buscarProducto( )
    {
		setupescenario1();
		categoria=categoria.buscarPadre("1111");
		Producto nod = categoria.buscarProducto("31759941");
		if (nod!=null)
				assertTrue(true);
		else
			fail("deberia encontrarse el producto");
    }
	@Test
    public void buscarProducto2( )
    {
		setupescenario2();
		Producto nod = categoria.buscarProducto("31759941");
		if (nod==null)
				assertTrue(true);
		else
			fail("no existe ningun producto en el catalogo aun");
    }

	@Test
    public void darProductos( )
    {
		setupescenario1();
		List nod = categoria.darProductos();
		if (nod!=null)
				assertTrue(true);
		else
			fail("deberia retornar una lista de productos");
    }
	@Test
    public void darProductos2( )
    {

		setupescenario2();
		List nod = categoria.darProductos();
		if (nod.size()==0)
				assertTrue(true);
		else
			fail("la lista deberia estar vacia");
    }
	@Test
    public void darMarcas( )
    {
		setupescenario1();
		List nod = categoria.darMarcas();
		if (nod.size()>0)
				assertTrue(true);
		else
			fail("deberia retornar una lista de categorias");

    }
	@Test
    public void darMarcas2( )
    {
		setupescenario2();
		List nod = categoria.darMarcas();
		if (nod.size()==0l)
				assertTrue(true);
		else
			fail("no deberia retornar una lista de categorias, puesto que no hay marcas");

    }
	@Test
    public void darPreorden( )
    {
		setupescenario1();
		List<NodoAlmacen> nod = categoria.darPreorden();
		if (nod.size()>0 && (nod.get(0).darIdentificador().compareTo(nod.get(1).darIdentificador())<0))
				assertTrue(true);
		else
			fail("deberia retornar una lista en preorden");
    }

	@Test
    public void darPosorden( )
    {
		setupescenario1();
		List<NodoAlmacen> nod = categoria.darPosorden();
		
		if ((nod.size()>0 && (nod.get(0).darIdentificador().compareTo(nod.get(1).darIdentificador())<0)) )
				assertTrue(true);
		else
			fail("deberia retornar una lista en posorden");
    }

	@Test
    public void darValorVentas( )
    {
		setupescenario1();
		categoria=categoria.buscarPadre("1111");
		double nod = categoria.darValorVentas();
		if (nod==0.0 )
				assertTrue(true);
		else
			fail("deberia retornar 0, pues no se ha vendido nada");
    }


}
