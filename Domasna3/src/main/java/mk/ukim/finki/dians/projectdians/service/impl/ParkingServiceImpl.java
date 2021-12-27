package mk.ukim.finki.dians.projectdians.service.impl;

import mk.ukim.finki.dians.projectdians.model.Parking;
import mk.ukim.finki.dians.projectdians.model.PlaceType;
import mk.ukim.finki.dians.projectdians.repository.ParkingRepository;
import mk.ukim.finki.dians.projectdians.repository.PlaceTypeRepository;
import mk.ukim.finki.dians.projectdians.service.ParkingService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {
    private final ParkingRepository parkingRepository;
    private final PlaceTypeRepository placeTypeRepository;
    public ParkingServiceImpl(ParkingRepository parkingRepository, PlaceTypeRepository placeTypeRepository){

        this.placeTypeRepository=placeTypeRepository;
        this.parkingRepository=parkingRepository;
    }

    @Override
    public List<Parking> findALlParking() {
        return parkingRepository.findAll();
    }

    @Override
    public Optional<Parking> saveParking(String lat, String lon, String name, String website, String adress, String openingHours) {
        Parking parking=new Parking(lat,lon,name,website,adress,openingHours);
        return Optional.of(parkingRepository.save(parking));
    }

    @Override
    public void deleteParking(Long id) {

        parkingRepository.deleteById(id);
    }

    @Override
    public Parking findById(Long id) {
        //probaj dali ke raboti ako vraka optional
    Parking parking1=parkingRepository.findById(id).orElse(null);
    PlaceType placeType=placeTypeRepository.findById(id).orElse(null);
    Parking finalParking=new Parking(id,placeType.getName(),placeType.getNumberOfPeopleRating(),placeType.getFinalRating(),parking1.getLat(),parking1.getLon(),parking1.getWebsite(),parking1.getAddr_street(),parking1.getOpening_hours());
    return finalParking;
    }

    @Override
    public Optional<Parking> editParking(Long id, String lat, String lon, String name, String website, String adress, String openingHours) {
        Parking parking=parkingRepository.findById(id).orElse(null);
        parking.setAddr_street(adress);
        parking.setLon(lon);
        parking.setLat(lat);
        parking.setWebsite(website);
        parking.setOpening_hours(openingHours);
        parking.setName(name);
        return Optional.of(parkingRepository.save(parking));
    }

    @Override
    public List<Parking> findAllByNameContains(String name) {
        return parkingRepository.findAllByNameContains(name);
    }

    @Override
    public List<Parking> sortAllByName() {

       return parkingRepository.findAllByOrderByNameAsc();
       // return parkingRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
        // return parkingRepository.findAll().stream().sorted((o1, o2)->o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
    }

    @Override
    public List<Parking> findAllByRatingAfter(double rating) {
        return parkingRepository.findAllByFinalRatingAfter(rating);
    }

    @Override
    public List<Parking> sortAllByRating() {
        return parkingRepository.findAllByOrderByFinalRatingAsc();
       // return parkingRepository.findAll(Sort.by(Sort.Direction.ASC,"website"));
        //return parkingRepository.findAll().stream().sorted((o1,o2)->Double.compare(o1.getFinalRating(),o2.getFinalRating())).collect(Collectors.toList());
    }
}
