package org.s3todb.fileprocessing.entityDTOMapper;

import org.springframework.stereotype.Component;
import org.s3todb.entity.HotelEntity;
import org.s3todb.fileprocessing.dto.HotelDto;

@Component
public class HotelDTOMapper {
    public static HotelEntity mapToEntity(HotelDto hotelDto){
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setName(hotelDto.getHotelName());
        hotelEntity.setHotelId(hotelDto.getHotelId());
        hotelEntity.setProviderId(hotelDto.getProviderId());


        return hotelEntity;
    }
}
