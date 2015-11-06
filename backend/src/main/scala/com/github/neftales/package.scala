package com.github

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

package object neftales {

  implicit class FutureOps[T](future: Future[T]) {
    def onFailureWithReturn[U](pf: PartialFunction[Throwable, U])(implicit executor: ExecutionContext): Future[T] = {
      future onFailure pf
      future
    }

    def onSuccessWithReturn[U](pf: PartialFunction[T, U])(implicit executor: ExecutionContext): Future[T] = {
      future onSuccess pf
      future
    }

    def onCompleteWithReturn[U](f: Try[T] => U)(implicit executor: ExecutionContext): Future[T] = {
      future onComplete f
      future
    }
  }

  implicit val applicationExecutor = ExecutionContext.fromExecutorService(
    Executors.newFixedThreadPool(6)
  )

}