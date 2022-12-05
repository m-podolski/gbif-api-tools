package com.github.mpodolski.gbifapitools;

import io.r2dbc.proxy.core.QueryExecutionInfo;
import io.r2dbc.proxy.listener.ProxyExecutionListener;
import io.r2dbc.proxy.support.QueryExecutionInfoFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingListener implements ProxyExecutionListener {

  private static final Logger logger = LoggerFactory.getLogger(LoggingListener.class);

  private final QueryExecutionInfoFormatter formatter = QueryExecutionInfoFormatter.showAll();

  @Override
  public void afterQuery(QueryExecutionInfo execInfo) {
    logger.info(this.formatter.format(execInfo));
  }

}
