package youda.component.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import youda.component.constants.Constants;
import youda.component.dao.CarouselDAO;
import youda.component.model.Carousel;
import youda.component.service.CarouselService;

@Component
public class CarouselServiceImpl implements CarouselService {

	@Autowired
	CarouselDAO carouselDao;
	
	@Override
	public List<Carousel> getCarouselList() {
		return carouselDao.getCarouselList();
	}

	@Override
	public Carousel getCarouselById(int id) {
		return carouselDao.getCarouselById(id);
	}

	@Override
	public int addCarousel(Carousel carousel) {
		int id = carouselDao.addCarousel(carousel);
		carousel.setId(id);
		carousel.setListorder(id);
		this.updateCarousel(carousel);
		return id;
	}

	@Override
	public int updateCarousel(Carousel carousel) {
		return carouselDao.updateCarousel(carousel);
	}

	@Override
	public void delCarousel(int id) {
		Carousel carousel = this.getCarouselById(id);
		File file = new File(Constants.ROOT_PATH + carousel.getPicpath());
		if(file.exists()){
			file.delete();
		}
		carouselDao.delCarousel(id);
	}

	@Override
	public void carouselMoveUp(int id) {
		Carousel carousel = this.getCarouselById(id);
		Carousel preCarousel = carouselDao.getPreCarousel(carousel.getListorder());
		if(preCarousel == null){
			return ;
		}
		
		int tmp = preCarousel.getListorder();
		preCarousel.setListorder(carousel.getListorder());
		this.updateCarousel(preCarousel);
		
		carousel.setListorder(tmp);
		this.updateCarousel(carousel);
	}

	@Override
	public void carouselMoveDown(int id) {
		Carousel carousel = this.getCarouselById(id);
		Carousel nextCarousel = carouselDao.getNextCarousel(carousel.getListorder());
		if(nextCarousel == null){
			return ;
		}
		
		int tmp = nextCarousel.getListorder();
		nextCarousel.setListorder(carousel.getListorder());
		this.updateCarousel(nextCarousel);
		
		carousel.setListorder(tmp);
		this.updateCarousel(carousel);
	}

}
