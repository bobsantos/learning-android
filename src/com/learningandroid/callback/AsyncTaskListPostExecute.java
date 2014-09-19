package com.learningandroid.callback;

import java.util.List;

public interface AsyncTaskListPostExecute<T extends List<?>> {
	void onPostExecute(List<T> t);
}
