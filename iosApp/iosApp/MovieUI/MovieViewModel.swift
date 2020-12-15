//
//  MovieViewModel.swift
//  iosApp
//
//  Created by myungpyo on 2021/01/10.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

public class MovieViewModel {
    
    @LazyInject
    private var movieSdk: MovieSDKForIOS
    
    @Published
    private(set) var movieViewData: ViewData<[Movie]> = .empty
    
    private var disposeBag = CFlowDisposebag()
    
    func serachMovies(with keyword: String) {
        do {
            cancelActiveCFlow()
            
            try movieSdk.searchMovies(keyword: keyword)
                .watch { (array: NSArray?) in
                    if let movieEntities = array as? [MovieEntity] {
                        let movies = movieEntities.map {
                            MovieMapper.shared.map(data: $0)
                        }
                        self.movieViewData = ViewData.success(movies)
                    }
                }.store(in: disposeBag)
        } catch {
            NSLog("Error \(error)")
            self.movieViewData = ViewData.fail(error)
        }
    }
    
    func loadScrapMovies() {
        do {
            cancelActiveCFlow()
            
            self.movieViewData = .inProgress
            
            try movieSdk.loadScrapMovies()
                .watch { (array: NSArray?) in
                    if let movieEntities = array as? [MovieEntity] {
                        
                        let movies = movieEntities.map {
                            MovieMapper.shared.map(data: $0)
                        }
                        
                        self.movieViewData = ViewData.success(movies)                        
                    }
                }.store(in: disposeBag)
        } catch {
            NSLog("Error \(error)")
            self.movieViewData = ViewData.fail(error)
        }
    }
    
    func updateScrop(id: Int64, scrap: Bool) {
        self.updateScrapInMemory(id: id, scrap: scrap)
        movieSdk.setScrapMovie(id: id, scrap: scrap) { (_, error: Error?) in
            if let _ = error {
                self.updateScrapInMemory(id: id, scrap: !scrap)
                //TODO notify error message
                return
            }
        
            // Success
        }
    }
    
    private func updateScrapInMemory(id: Int64, scrap: Bool) {
        if case let ViewData.success(movies) = self.movieViewData {
            let updatedMovies = movies.map { (movie: Movie) -> Movie in
                if (movie.id == id) {
                    return movie.copy(scrap: scrap)
                } else {
                    return movie
                }
            }
            
            self.movieViewData = ViewData.success(updatedMovies)
        }
    }
    
    private func cancelActiveCFlow() {
        self.disposeBag.dispose()
        self.disposeBag = CFlowDisposebag()
    }
    
    deinit {
        disposeBag.dispose()
    }
}
