package com.evgen.payment.service.api;

import com.evgen.payment.model.News;
import com.evgen.payment.model.User;

public interface VkNewsService {

  News getVkNews(User user);

}