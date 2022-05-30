package uniandes.cupi2.almacen.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import uniandes.cupi2.almacen.mundo.Almacen;
import uniandes.cupi2.almacen.mundo.AlmacenException;
import uniandes.cupi2.almacen.mundo.Categoria;
import uniandes.cupi2.almacen.mundo.NodoAlmacen;
import uniandes.cupi2.almacen.mundo.Producto;

public class AlmacenTest {

	private Almacen almacen; 

	private void setupescenario1()
	{
		try {
			almacen = new Almacen(new File("./data/datos.txt"));
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void darCategoriaRaizTest()
	{
		setupescenario1();
		Categoria c=almacen.darCategoriaRaiz();
		if (c!=null)
		{
			assertTrue( true );
		}
		else
		{
			fail( "El archivo debia estar cargado" );
		}
	}


	@Test
	public void agregarNodoTest() 
	{
		setupescenario1();
		try {
			almacen.agregarNodo("11", "Categoria", "2025", "lavadoras");
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodoAlmacen s = almacen.buscarNodo("2025");
		if( s == null )
			fail( "El nodo debería existir" );
		else
		{
			assertEquals( "2025", s.darIdentificador());
		}

	}
	
	@Test
	public void agregarNodoTest2() 
	{
		setupescenario1();
		try {
			almacen.agregarNodo("1109", "Categoria", "2025", "lavadoras");
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodoAlmacen s = almacen.buscarNodo("2025");
		if( s != null )
			fail( "El nodo no debería existir" );
		else
		{
			assertTrue(true);
		}

	}

	@Test
	public void eliminarNodoTest( )
	{
		setupescenario1();
		try {
			almacen.agregarNodo("11", "Categoria", "2025", "lavadoras");
			almacen.eliminarNodo("2025");
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodoAlmacen s = almacen.buscarNodo("2025");
		if( s == null )
			assertTrue(true);
		else
		{
			fail("el nodo no deberia de existir");
		}


	}
	@Test
	public void eliminarNodoTest2( )
	{
		setupescenario1();
		try {
			almacen.agregarNodo("11", "Categoria", "2025", "lavadoras");
			almacen.eliminarNodo("202520");
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodoAlmacen s = almacen.buscarNodo("2025");
		if( s != null )
			assertTrue(true);
		else
		{
			fail("el nodo deberia de existir");
		}


	}
	@Test
	public void venderProductoTest()
	{
		setupescenario1();
		almacen.venderProducto("24881271", 3);
		Producto s = almacen.darCategoriaRaiz().buscarProducto("24881271");
		if( s != null && s.darCantidadUnidadesVendidas()==3 )
		{
			assertTrue(true);
		}
		else
		{
			fail("el producto deberia de existir o, tener 3 ventas");
		}

	}
	@Test
	public void venderProductoTest2()
	{
		setupescenario1();
		almacen.venderProducto("24881271", 1);
		Producto s = almacen.darCategoriaRaiz().buscarProducto("24881271");
		if( s != null && s.darCantidadUnidadesVendidas()==3 )
		{
			fail("el producto deberia de existir y no tener 3 ventas");
			
		}
		else if (s!=null)
		{
			assertTrue(true);
		}

	}

	@Test
	public void buscarNodoTest( )
	{
		setupescenario1();
		NodoAlmacen s = almacen.buscarNodo("2025");
		if( s == null )
			assertTrue(true);
		else
		{
			fail("no deberia existir el nodo");
		}


	}

	@Test
	public void buscarNodoTest2( )
	{
		setupescenario1();
		NodoAlmacen s = almacen.buscarNodo("111");
		if( s != null )
			assertTrue(true);
		else
		{
			fail("deberia existir el nodo");
		}


	}
	@Test
	public void agregarProductoTest(  )
	{

		setupescenario1();
		try {
			almacen.agregarProducto("1111", "24881275", "lavadora", "disfruta de tu lavada", 1000000);
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Producto s = almacen.darCategoriaRaiz().buscarProducto("24881275");
		if( s != null )
			assertTrue(true);
		else
		{
			fail("el producto deberia de existir");
		}
	}
	
	@Test
	public void agregarProductoTest2(  )
	{

		setupescenario1();
		try {
			almacen.agregarProducto("1111", "24881275", "lavadora", "disfruta de tu lavada", 1000000);
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Producto s = almacen.darCategoriaRaiz().buscarProducto("24881");
		if( s == null )
			assertTrue(true);
		else
		{
			fail("el producto no deberia de existir");
		}
	}

	@Test
	public void eliminarProductoTest(){
		setupescenario1();
		try {
			almacen.agregarProducto("1111", "24881275", "lavadora", "disfruta de tu lavada", 1000000);
			almacen.eliminarProducto("24881275");
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Producto s = almacen.darCategoriaRaiz().buscarProducto("24881275");
		if( s == null )
			assertTrue(true);
		else
		{
			fail("el producto no deberia de existir");
		}
	}
	@Test
	public void eliminarProductoTest2(){
		setupescenario1();
		try {
			almacen.agregarProducto("1111", "24881275", "lavadora", "disfruta de tu lavada", 1000000);
		} catch (AlmacenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Producto s = almacen.darCategoriaRaiz().buscarProducto("24881275");
		if( s != null )
			assertTrue(true);
		else
		{
			fail("el producto no deberia de eliminarse");
		}
	}
}
