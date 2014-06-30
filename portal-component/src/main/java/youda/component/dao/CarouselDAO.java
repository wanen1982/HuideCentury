package youda.component.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import youda.component.model.Carousel;

@DAO
public interface CarouselDAO {

	final static String carousel = "id,picpath,title,description,linkUrl,listorder";
	
	@SQL("select " + carousel + " from carousel order by listorder")
	public List<Carousel> getCarouselList();
	
	@SQL("select " + carousel + " from carousel where id = :1")
	public Carousel getCarouselById(int id);
	
	@ReturnGeneratedKeys
	@SQL("insert into carousel(picpath,title,description,linkUrl,listorder) values (:1.picpath,:1.title,:1.description,:1.linkUrl,:1.listorder)")
	public int addCarousel(Carousel carousel);
	
	@SQL("update carousel set picpath=:1.picpath,title=:1.title,description=:1.description,linkUrl=:1.linkUrl,listorder=:1.listorder where id=:1.id")
	public int updateCarousel(Carousel carousel);
	
	@SQL("delete from carousel where id = :1")
	public void delCarousel(int id);
	
	@SQL("select " + carousel + " from carousel where listorder < :1 order by listorder desc limit 1")
	public Carousel getPreCarousel(int listorder);
	
	@SQL("select " + carousel + " from carousel where listorder > :1 order by listorder limit 1")
	public Carousel getNextCarousel(int listorder);
}
