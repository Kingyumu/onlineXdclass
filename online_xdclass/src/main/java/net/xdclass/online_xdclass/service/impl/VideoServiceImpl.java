package net.xdclass.online_xdclass.service.impl;

import net.xdclass.online_xdclass.config.CacheKeyManager;
import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoBanner;
import net.xdclass.online_xdclass.mapper.VideoMapper;
import net.xdclass.online_xdclass.service.VideoService;
import net.xdclass.online_xdclass.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;

    @Override
    public List<Video> listVideo() {

        try {

            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_LIST, () -> {

                return videoMapper.listVideo();
            });

            if (cacheObj instanceof List){

                List<Video> videoList = (List<Video>) cacheObj;
                return videoList;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<VideoBanner> listBanner() {

        try {

            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER, () -> {

                List<VideoBanner> bannerList = videoMapper.listVideoBanner();
                return bannerList;
            });

            if (cacheObj instanceof List){

                List<VideoBanner> bannerList = (List<VideoBanner>) cacheObj;
                return bannerList;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Video findDetailById(int videoId) {

        String videoCacheKey = String.format(CacheKeyManager.VIDEO_DETAIL, videoId);

        try {

            Object cacheObj = baseCache.getOneHourCache().get(videoCacheKey, () -> {

                return videoMapper.findDetailById(videoId);
            });

            if (cacheObj instanceof Video){
                return (Video) cacheObj;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

}
