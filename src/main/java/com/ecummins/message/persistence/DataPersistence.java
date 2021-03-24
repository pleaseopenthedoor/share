package com.ecummins.message.persistence;

import java.util.List;

import com.ecummins.message.record.MessageRecord;

/**
 * 数据持久化接口
 */
public interface DataPersistence {

	void doPersistence(List<MessageRecord> messageRecord);
}
