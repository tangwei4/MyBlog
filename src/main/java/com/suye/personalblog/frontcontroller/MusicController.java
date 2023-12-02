package com.suye.personalblog.frontcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/front")
public class MusicController {
    @GetMapping("/music")
    public List<Music> getMusicList() {
        // 读取音乐文件夹中的文件信息
        String musicFolderPath = "src/main/resources/static/front/music";
        List<Music> musicList = new ArrayList<>();

        File folder = new File(musicFolderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".mp3")) {
                    // 从文件名中提取音乐信息，这里需要根据实际情况进行处理
                    String[] parts = file.getName().split(" - ");
                    if (parts.length == 2) {
                        musicList.add(new Music(parts[1].replace(".mp3", ""), parts[0], file.getName()));
                    }
                }
            }
        }

        return musicList;
    }

    // 音乐类，包含标题、作者和文件名
    static class Music {
        private String title;
        private String author;
        private String fileName;

        public Music(String title, String author, String fileName) {
            this.title = title;
            this.author = author;
            this.fileName = fileName;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getFileName() {
            return fileName;
        }
    }
}
