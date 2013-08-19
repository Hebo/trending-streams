package com.github.hebo.streamtrends

import scalaz._, Scalaz._
import argonaut._, Argonaut._

case class Preview(
  medium: String,
  large: String,
  small: String,
  template: String
)

object Preview {
  implicit def PreviewCodecJson: CodecJson[Preview] =
      casecodec4(Preview.apply, Preview.unapply)(
        "medium", "large", "small", "template"
      )
}

case class ChannelLinks(
  stream_key: String,
  videos: String,
  editors: String,
  follows: String,
  teams: String,
  chat: String,
  subscriptions: String,
  self: String,
  features: String,
  commercial: String
)

object ChannelLinks {
  implicit def ChannelLinksCodecJson: CodecJson[ChannelLinks] =
      casecodec10(ChannelLinks.apply, ChannelLinks.unapply)(
        "stream_key",
        "videos",
        "editors",
        "follows",
        "teams",
        "chat",
        "subscriptions",
        "self",
        "features",
        "commercial"
      )
}

case class StreamLinks(
  self: String
)

object StreamLinks {
  implicit def StreamLinksCodecJson: CodecJson[StreamLinks] =
      casecodec1(StreamLinks.apply, StreamLinks.unapply)("self")
}

case class Channel(
  url: String,
  background: String,
  status: String,
  game: String,
  delay: Int,
  profile_banner: String,
  display_name: String,
  _id: Int,
  created_at: String,
  mature: String,
  name: String,
  _links: ChannelLinks,
  logo: String
)

object Channel {
  implicit def ChannelCodecJson: CodecJson[Channel] =
      casecodec13(Channel.apply, Channel.unapply)(
        "url",
        "background",
        "status",
        "game",
        "delay",
        "profile_banner",
        "display_name",
        "_id",
        "created_at",
        "mature",
        "name",
        "_links",
        "logo"
      )
}

case class TwitchStream(
  viewers: Int,
  game: String,
  preview: Preview,
  channel: Channel,
  _id: Int,
  _links: StreamLinks
)

object TwitchStream {
  implicit def TwitchStreamCodecJson: CodecJson[TwitchStream] =
      casecodec6(TwitchStream.apply, TwitchStream.unapply)(
        "viewers",
         "game",
         "preview",
         "channel",
         "_id",
         "_links"
      )
}

case class TwitchStreams(
  streams: List[TwitchStream]
)

object TwitchStreams {
  implicit def TwitchStreamsCodecJson: CodecJson[TwitchStreams] =
      casecodec1(TwitchStreams.apply, TwitchStreams.unapply)("streams")
}
