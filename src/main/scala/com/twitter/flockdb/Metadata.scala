/*
 * Copyright 2010 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.twitter.flockdb

import com.twitter.util.Time

case class Metadata(sourceId: Long, state: State, count: Int, updatedAt: Time) extends Ordered[Metadata] {
  def compare(other: Metadata) = {
    val out = updatedAt.compare(other.updatedAt)
    if (out == 0) {
      state.compare(other.state)
    } else {
      out
    }
  }
  
  def max(other: Metadata) = if (this > other) this else other
}