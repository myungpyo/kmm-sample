//
//  MovieMapper.swift
//  iosApp
//
//  Created by myungpyo on 2021/01/10.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class MovieMapper: Mapper {
    typealias From = MovieEntity
    typealias To = Movie
    
    static let shared = MovieMapper()
    
    func map(data: MovieEntity) -> Movie {
        Movie(
            id: data.id,
            title: data.title,
            attributedTitle: makeHtmlString(text: data.title),
            subTitle: data.subTitle,
            attributedSubtitle: makeHtmlStringOrNil(text: data.subTitle),
            thumb: data.thumb,
            director: data.director,
            actor: data.actor,
            publishedAt: data.publishedAt,
            rating: data.rating,
            scrap: data.scrap,
            memo: data.memo
        )
    }
    
    func reverse(data: Movie) -> MovieEntity {
        MovieEntity(
            id: data.id,
            title: data.title,
            subTitle: data.subTitle,
            thumb: data.thumb,
            director: data.director,
            actor: data.actor,
            publishedAt: data.publishedAt,
            rating: data.rating,
            scrap: data.scrap,
            memo: data.memo
        )
    }
    
    private func makeHtmlString(text: String) -> NSAttributedString {
        return makeHtmlStringOrNil(text: text)!
    }
    
    private func makeHtmlStringOrNil(text: String?) -> NSAttributedString? {
        guard let data = text?.data(using: String.Encoding.unicode) else { return nil }
        return try? NSAttributedString(data: data,
                           options: [.documentType:NSAttributedString.DocumentType.html],
                documentAttributes: nil)
    }
}
