package cn.liu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDTO implements Serializable {
    private  String title ; //音频集数
    private Integer download_url ;//下载地址
    private String size  ; //音频大小
    private String duration ; //音频播放时长

}
