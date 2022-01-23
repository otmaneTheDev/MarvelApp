package com.example.domain.mappers

import com.example.data.models.*
import com.example.domain.models.*
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun map(dto: CharacterDataContainerDto): CharacterDataContainer {
        return CharacterDataContainer(
            dto.count,
            dto.limit,
            dto.offset,
            dto.characters.map { map(it) },
            dto.total
        )
    }

    fun map(dto: CharacterDto): Character {
        return Character(
            map(dto.comics),
            dto.description,
            map(dto.events),
            dto.id,
            dto.modified,
            dto.name,
            dto.resourceURI,
            map(dto.series),
            map(dto.stories),
            map(dto.thumbnail),
            dto.urls.map { map(it) }
        )
    }

    fun map(it: UrlDto): Url {
        return Url(
            it.type,
            it.url
        )
    }

    fun map(thumbnail: ThumbnailDto): Thumbnail {
        return Thumbnail(
            thumbnail.extension,
            thumbnail.path
        )
    }

    fun map(stories: StoriesDto): Stories {
        return Stories(
            stories.available,
            stories.collectionURI,
            stories.items.map { map(it) },
            stories.returned
        )
    }

    fun map(it: SummaryDto): Summary {
        return Summary(
            it.name,
            it.resourceURI,
            it.type
        )
    }

    fun map(series: SeriesDto): Series {
        return Series(
            series.available,
            series.collectionURI,
            series.items.map { map(it) },
            series.returned
        )
    }

    fun map(events: EventsDto): Events {
        return Events(
            events.available,
            events.collectionURI,
            events.items.map { map(it) },
            events.returned
        )
    }

    fun map(comics: ComicsDto): Comics {
        return Comics(
            comics.available,
            comics.collectionURI,
            comics.items.map { map(it) },
            comics.returned
        )
    }

}