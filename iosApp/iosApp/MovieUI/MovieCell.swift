//
//  MovieCell.swift
//  iosApp
//
//  Created by myungpyo on 2021/01/07.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import UIKit
import shared
import Nuke

class MovieCell: UITableViewCell {
    
    
    @IBOutlet weak var thumbnail: UIImageView?
    @IBOutlet weak var title: UILabel?
    @IBOutlet weak var subtitle: UILabel?
    @IBOutlet weak var publishedAt: UILabel?
    @IBOutlet weak var director: UILabel?
    @IBOutlet weak var actors: UILabel?
    @IBOutlet weak var scrapButton: UIButton?
    
    weak var delegate: MovieCellDelegate?
    
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
        self.title?.text = ""
        self.subtitle?.text = ""
        self.publishedAt?.text = ""
        self.director?.text = ""
        self.actors?.text = ""
        self.scrapButton?.isSelected = false
        self.thumbnail?.image = nil
    }
    
    var movie: Movie? {
        didSet {
            if let movie = self.movie {
                self.title?.attributedText = movie.attributedTitle
                self.subtitle?.attributedText = movie.attributedSubtitle
                self.publishedAt?.text = movie.publishedAt
                self.director?.text = movie.director
                self.actors?.text = movie.actor
                
                if let thumb = movie.thumb, let url = URL(string: thumb), let thumbTarget = self.thumbnail {
                    Nuke.loadImage(with: url, into: thumbTarget)
                }
                
                self.scrapButton?.isSelected = movie.scrap
            }
        }
    }
    
    @IBAction func onToggleScrap(_ sender: Any) {
        if let movie = self.movie {
            self.delegate?.movieCell(self, toggleScrapOf: movie)
        }
    }
}

protocol MovieCellDelegate: class {
    func movieCell(_ cell: MovieCell, toggleScrapOf movie: Movie)
}
