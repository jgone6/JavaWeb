package org.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.mreview.entity.MovieImage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long mno;

    private String title;

    private String content;

    @Builder.Default
    private List<MovieImageDTO> imageDTOList = new ArrayList<>();

    //영화의 평균 평점
    private double avg;

    //리뷰 수 jpa의 count
    private int reviewCnt;

    private LocalDateTime regDate, modDate;
}
