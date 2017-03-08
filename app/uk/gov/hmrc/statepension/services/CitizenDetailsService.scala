/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.statepension.services

import uk.gov.hmrc.domain.Nino
import uk.gov.hmrc.play.http.HeaderCarrier
import uk.gov.hmrc.statepension.connectors.CitizenDetailsConnector
import uk.gov.hmrc.play.http.logging.MdcLoggingExecutionContext._

import scala.concurrent.Future

trait CitizenDetailsService {
  val citizenDetailsConnector: CitizenDetailsConnector

  def checkManualCorrespondenceIndicator(nino: Nino)(implicit hc: HeaderCarrier): Future[Boolean] = {
    citizenDetailsConnector.connectToGetPersonDetails(nino).map(status => status == play.api.http.Status.LOCKED)
  }
}
