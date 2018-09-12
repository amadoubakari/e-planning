package com.flys.eplanning.dao.service;

import android.util.Log;
import com.flys.eplanning.architecture.core.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rx.Observable;
import rx.Subscriber;

public abstract class AbstractDao {

  // mappeur jSON
  private ObjectMapper mapper = new ObjectMapper();
  // mode debug
  protected boolean isDebugEnabled;
  // nom de la classe
  protected String className;
  // délay d'attente avant exécution requête
  private int delay;

  // constructeur
  public AbstractDao() {
    // nom de la classe
    className = getClass().getName();
    Log.d("AbstractDao", String.format("constructeur, thread=%s", Thread.currentThread().getName()));
  }

  // méthodes protégées ----------------------------------------------------------
  // interface générique
  protected interface IRequest<T> {
    T getResponse();
  }

  // requête générique vers un service jSON
  protected <T> Observable<T> getResponse(final IRequest<T> request) {
    // log
    if (isDebugEnabled) {
      Log.d(String.format("%s", className), String.format("delay=%s", delay));
    }
    // exécution service - on attend une unique réponse
    return Observable.create(new Observable.OnSubscribe<T>() {
      @Override
      public void call(Subscriber<? super T> subscriber) {
        DaoException ex = null;
        // exécution service
        try {
          // attente ?
          if (delay > 0) {
            Thread.sleep(delay);
          }
          // on exécute la requête synchrone
          T response = request.getResponse();
          // log
          if (isDebugEnabled) {
            String log;
            if (response instanceof String) {
              log = (String) response;
            } else {
              log = mapper.writeValueAsString(response);
            }
            Log.d(className, String.format("response=%s sur thread [%s]", log, Thread.currentThread().getName()));
          }
          // on émet la réponse vers l'observateur
          subscriber.onNext(response);
          // on signale la fin de l'observable
          subscriber.onCompleted();
        } catch (InterruptedException | JsonProcessingException | RuntimeException e) {
          // log
          if (isDebugEnabled) {
            try {
              Log.d(className, String.format("Thread [%s], Exception communication avec serveur : [%s,%s]", Thread.currentThread().getName(), e.getClass().getName(), mapper.writeValueAsString(Utils.getMessagesFromException(e))));
            } catch (JsonProcessingException e1) {
              Log.d(className, String.format("Erreur jSON imprévue"));
            }
          }
          // on émet une exception
          subscriber.onError(new DaoException(e, 100));
        }
      }
    });
  }

  // mode debug
  public void setDebugMode(boolean isDebugEnabled) {
    this.isDebugEnabled = isDebugEnabled;
  }

  public void setDelay(int delay) {
    this.delay = delay;
  }
}
