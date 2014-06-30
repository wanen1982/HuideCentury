package youda.component.service;

import java.util.List;

import youda.component.model.Carousel;

public interface CarouselService {

	public List<Carousel> getCarouselList();
	
	public Carousel getCarouselById(int id);
	
	public int addCarousel(Carousel carousel);
	
	public int updateCarousel(Carousel carousel);
	
	public void delCarousel(int id);
	
	public void carouselMoveUp(int id);
	
	public void carouselMoveDown(int id);
}
