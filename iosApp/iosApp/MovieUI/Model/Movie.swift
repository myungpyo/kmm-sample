//
//  Movie.swift
//  iosApp
//
//  Created by myungpyo on 2021/01/10.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

struct Movie {
    let id: Int64
    let title: String
    let attributedTitle: NSAttributedString
    let subTitle: String?
    let attributedSubtitle: NSAttributedString?
    let thumb: String?
    let director: String?
    let actor: String?
    let publishedAt: String?
    let rating: Float
    let scrap: Bool
    let memo: String?
    
    func copy(scrap: Bool? = nil, memo: String? = nil)-> Movie {
        return Movie(
            id: self.id,
            title: self.title,
            attributedTitle: self.attributedTitle,
            subTitle: self.subTitle,
            attributedSubtitle: self.attributedSubtitle,
            thumb: self.thumb,
            director: self.director,
            actor: self.actor,
            publishedAt: self.publishedAt,
            rating: self.rating,
            scrap: scrap ?? self.scrap,
            memo: memo ?? self.memo
        )
    }
}
