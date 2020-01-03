package cn.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Chapter implements Serializable {
    private String id  ; //音频id
    private String title ; //音频章节名字
    private String album_id ; //所属专辑id
    private String size ; //音频大小
    private String duration ; //音频播放时长
    private String src ; //音频路径
    private Integer status ; //音频状态
    private String other ; //预留字段
 }
